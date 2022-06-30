package SemanticAnalyzer.SymbolExpressions.PreFix.Name;

import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.JScope.Scopes.ClassScope;
import SemanticAnalyzer.JScope.Scopes.MethodScope;
import SemanticAnalyzer.Models.ParameterModel;
import SemanticAnalyzer.Models.ParentClassModel;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import java.util.*;

public final class NamePrefixExpressionExtractor {

    public NamePrefixExpressionExtractor() {}

    public Optional<NamePreExpressionMeta> checkNameExpression(NamePreExpression namePreExpression, Stack<Scope> currentExpressionScopeStack, List<Scope> allScopes) {
        if (namePreExpression.getId().equals("self")) {
            for (Scope classScope: currentExpressionScopeStack) {
                if (classScope.getScopeType() == ScopeType.classs) {
                    return Optional.of(
                            new NamePreExpressionMeta(
                                    classScope,
                                    Optional.empty(),
                                    classScope.getScopeName(),
                                    false,
                                    false,
                                    true
                            )
                    );
                }
            }
        }
        else {
            // method params
            Optional<NamePreExpressionMeta> methodParameterCheck = this.checkMethodParameters(namePreExpression, currentExpressionScopeStack);
            if (methodParameterCheck.isPresent()) return methodParameterCheck;

            // symbol tables of scopes on top of this expression
            Optional<NamePreExpressionMeta> symbolTablesCheck = this.checkNameOnSymbolTables(namePreExpression, currentExpressionScopeStack);
            if (symbolTablesCheck.isPresent()) return symbolTablesCheck;

            // parent class
            // first find program scope of this expression and its parent classes
            Optional<NamePreExpressionMeta> parentClassesCheck = this.checkParentClasses(namePreExpression, currentExpressionScopeStack, allScopes);
            if (parentClassesCheck.isPresent()) return parentClassesCheck;

            return Optional.empty();
        }
        return Optional.empty();
    }

    private Optional<NamePreExpressionMeta> checkParentClasses(NamePreExpression namePreExpression, Stack<Scope> currentExpressionScopeStack, List<Scope> allScopes) {
        Optional<Scope> programScope = this.findProgramScope(currentExpressionScopeStack);
        if (programScope.isPresent()) {
            List<ParentClassModel> parentClassesScopes = this.findParentClasses(currentExpressionScopeStack);
            for (Scope classScope : this.findSpecificClassesScope(allScopes, programScope.get(), parentClassesScopes)) {
                for (Map.Entry<String, SymbolValue> entry : classScope.getSymbolTable().getEntrySet()) {
                    if (entry.getKey().equals(namePreExpression.getId())) {
                        // found id
                        return Optional.of(
                                new NamePreExpressionMeta(
                                        classScope,
                                        Optional.of(entry.getValue()),
                                        entry.getValue().getType(),
                                        false,
                                        true,
                                        false
                                )
                        );
                    }
                }
            }
        }
        return Optional.empty();
    }

    private Optional<NamePreExpressionMeta> checkNameOnSymbolTables(NamePreExpression namePreExpression, Stack<Scope> currentExpressionScopeStack) {
        for (Scope scope: currentExpressionScopeStack) {
            for (Map.Entry<String, SymbolValue> entry : scope.getSymbolTable().getEntrySet()) {
                if (entry.getKey().equals(namePreExpression.getId())) {
                    // found id
                    return Optional.of(
                            new NamePreExpressionMeta(
                                    scope,
                                    Optional.of(entry.getValue()),
                                    entry.getValue().getType(),
                                    true,
                                    false,
                                    false
                            )
                    );
                }
            }
        }
        return Optional.empty();
    }

    private Optional<NamePreExpressionMeta> checkMethodParameters(NamePreExpression namePreExpression, Stack<Scope> currentExpressionScopeStack) {
        if (currentExpressionScopeStack.peek().getScopeType() == ScopeType.method) {
            MethodScope methodScope = (MethodScope) currentExpressionScopeStack.peek();
            for (ParameterModel parameterModel : methodScope.getParameters()) {
                if (parameterModel.name().equals(namePreExpression.getId())) {
                    // found id in method parameters
                    return Optional.of(
                            new NamePreExpressionMeta(
                                    methodScope,
                                    Optional.empty(),
                                    parameterModel.type(),
                                    true,
                                    false,
                                    false
                            )
                    );
                }
            }
        }
        return Optional.empty() ;
    }

    private List<Scope> findSpecificClassesScope(List<Scope> allScopes, Scope programScope, List<ParentClassModel> parentClasses) {
        List<Scope> scopeList = new ArrayList<>();
        for (Scope scope : allScopes){
            if (scope.hashCode() != programScope.hashCode() && scope.getScopeType() == ScopeType.program) {
                scopeList.addAll(
                        scope.getAllChildScopes().parallelStream()
                                .filter(s -> s.getScopeType() == ScopeType.classs)
                                .filter(s ->
                                        parentClasses.stream()
                                                .map(ParentClassModel::name)
                                                .toList()
                                                .contains(s.getScopeName())
                                )
                                .toList()
                );
            }
        }
        return new ArrayList<>();
    }

    private Optional<Scope> findProgramScope(Stack<Scope> currentExpressionScopeStack) {
        for (Scope scope : currentExpressionScopeStack)
            if (scope.getScopeType() == ScopeType.program) return Optional.of(scope);
        return Optional.empty();
    }

    private List<ParentClassModel> findParentClasses(Stack<Scope> currentExpressionScopeStack) {
        for (Scope scope : currentExpressionScopeStack){
            if (scope.getScopeType() == ScopeType.classs) {
                ClassScope classScope = (ClassScope) scope;
                return classScope.getClassParents();
            }
        }
        return new ArrayList<>();
    }
}

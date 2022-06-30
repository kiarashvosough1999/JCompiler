package SemanticAnalyzer.SymbolExpressions.Factories;

import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.JScope.Scopes.MethodScope;
import SemanticAnalyzer.Validators.ValidationResultModel;
import gen.JythonParser;
import java.util.List;
import java.util.Optional;

public class ClassInstanceFactory {

    private final Optional<String> shouldReturnType;

    public ClassInstanceFactory(Optional<String> shouldReturnType) {
        this.shouldReturnType = shouldReturnType;
    }

    public ValidationResultModel validateClassInstance(JythonParser.Class_instanceContext context, List<Scope> scopeList) {
        for (Scope scope: scopeList) {
            if (scope.getScopeType() == ScopeType.classs &&
                    context.className.getText().equals(scope.getScopeName()) &&
                    this.shouldReturnType.map( s -> s.equals(scope.getScopeName())).orElse(true)
            ) {
                for (Scope childScope: scope.getAllChildScopes()) {
                    if (childScope.getScopeType() == ScopeType.constructor) {
                        MethodScope constructor = (MethodScope)childScope;
                        return new ArgsFactory(shouldReturnType).generateArgsList(
                                context.args(),
                                scopeList,
                                constructor.getParameters(),
                                constructor.getNamePosition(),
                                constructor.getScopeName()
                        );
                    }
                }
            }
        }
        return new ValidationResultModel(
                true,
                null,
                null,
                null,
                ""
        );
    }
}

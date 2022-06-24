package SemanticAnalyzer.Validators.Usage;

import SemanticAnalyzer.Helper;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.Models.ClassErrorMeta;
import SemanticAnalyzer.Models.ClassUsageErrorModel;
import SemanticAnalyzer.Models.FieldErrorMeta;
import SemanticAnalyzer.Models.MethodErrorMeta;
import SemanticAnalyzer.SymbolValues.SymbolValueKind;
import SemanticAnalyzer.Validators.ErrorProneEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassUsageValidator {

    public ClassUsageValidator() {
    }

    private List<String> extractClasses(List<Scope> scopes) {
        List<String> availableClasses = new ArrayList<>();
        for (Scope scope: scopes) {
            if (scope.getScopeType() == ScopeType.program) {
                availableClasses.addAll(
                        scope.getSymbolTable()
                                .getEntrySet()
                                .parallelStream()
                                .filter(s -> s.getValue().getKind() == SymbolValueKind.importt)
                                .map(Map.Entry::getKey)
                                .toList()
                );

                availableClasses.addAll(
                        scope.getAllChildScopes()
                                .parallelStream()
                                .filter(s -> s.getScopeType() == ScopeType.classs)
                                .map(Scope::getScopeName)
                                .toList()
                );
            }
        }
        return availableClasses;
    }

    public void validateClasses(List<Scope> scopes) {

        List<String> availableClasses = this.extractClasses(scopes);
        for (ClassUsageErrorModel errorModel: this.depthFirstClassCheck(scopes, availableClasses)) {
            System.out.println(errorModel.toString());
        }
    }

    public List<ClassUsageErrorModel> depthFirstClassCheck(List<Scope> scopeList, List<String> availableClasses) {

        if (scopeList.isEmpty()) { return new ArrayList(); }
        List<ClassUsageErrorModel> classUsageErrorModelList = new ArrayList<>();

        for (Scope scope: scopeList) {
            // check symbol table
            classUsageErrorModelList.addAll(
                    scope.getSymbolTable()
                            .getEntrySet()
                            .parallelStream()
                            .filter(s -> s.getValue().getKind() == SymbolValueKind.field || s.getValue().getKind() == SymbolValueKind.arrayField)
                            .map(Map.Entry::getValue)
                            .filter(s -> s.getType() != null)
                            .filter(s -> !Helper.isPrimitiveType(s.getType()))
                            .map(s -> (ErrorProneEntity)s)
                            .map(s -> (FieldErrorMeta)s.getErrorProneEntityMeta())
                            .filter(s -> !availableClasses.contains(s.type()))
                            .map(s -> new ClassUsageErrorModel(
                                            s.type(),
                                            s.namePosition()
                                    )
                            )
                            .toList()
            );
            // check method/constructor parameters
            if (scope.getScopeType() == ScopeType.method || scope.getScopeType() == ScopeType.constructor) {
                MethodErrorMeta methodErrorMeta = (MethodErrorMeta)((ErrorProneEntity)scope).getErrorProneEntityMeta();
                classUsageErrorModelList.addAll(
                        methodErrorMeta.parameters()
                                .parallelStream()
                                .filter(s -> s.type() != null)
                                .filter(s -> !Helper.isPrimitiveType(s.type()))
                                .filter(s -> !availableClasses.contains(s.type()))
                                .map(s -> new ClassUsageErrorModel(
                                                s.type(),
                                                s.namePosition()
                                        )
                                )
                                .toList()
                );
            }
            // check parent classes
            else if (scope.getScopeType() == ScopeType.classs) {
                ClassErrorMeta classErrorMeta = (ClassErrorMeta)((ErrorProneEntity)scope).getErrorProneEntityMeta();
                classUsageErrorModelList.addAll(
                        classErrorMeta.classParents()
                                .parallelStream()
                                .filter(s -> !Helper.isPrimitiveType(s.name()))
                                .filter(s -> !availableClasses.contains(s.name()))
                                .map(s -> new ClassUsageErrorModel(
                                                s.name(),
                                                s.namePosition()
                                        )
                                )
                                .toList()
                );
            }
            // check child scopes
            classUsageErrorModelList.addAll(
                    this.depthFirstClassCheck(scope.getAllChildScopes(), availableClasses)
            );
        }
        return classUsageErrorModelList;
    }
}

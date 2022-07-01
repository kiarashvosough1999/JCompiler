package SemanticAnalyzer.Validators.Redundancy;

import Constants.Constants;
import SemanticAnalyzer.Exceptions.IncorrectDetectorExeception;
import SemanticAnalyzer.Exceptions.ScopeTypeInConsistencyException;
import SemanticAnalyzer.Models.MethodErrorMeta;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.JScope.Scopes.MethodScope;
import SemanticAnalyzer.Models.ParameterModel;
import SemanticAnalyzer.Validators.ErrorProneEntity;
import SemanticAnalyzer.Models.ValidationResultModel;
import SemanticAnalyzer.Validators.ValidationStrategy;
import SemanticAnalyzer.Validators.ValidationTypes;
import java.util.List;

public class RedundantMethodStrategy implements ValidationStrategy {

    private final List<Scope> scopes;

    public RedundantMethodStrategy(List<Scope> scopes, MethodScope methodScope) {
        this.scopes = scopes;
    }

    @Override
    public ValidationResultModel checkValidity(ErrorProneEntity errorProneEntity) throws Exception {
        for (Scope scope: this.scopes) {
            // find class scope which contains methods
            if (scope.getScopeType() == ScopeType.classs) {
                // check all children for methods
                for (Scope childScope: scope.getAllChildScopes()) {
                    if (childScope.getScopeType() == ScopeType.method) {
                        // detect
                        ValidationResultModel validationResultModel = this.detectIdenticality(
                                childScope,
                                errorProneEntity
                        );
                        if (validationResultModel != null) return validationResultModel;
                    }
                }
            }
        }
        return new ValidationResultModel(
                true,
                null,
                null,
                null,
                null
        );
    }

    private String generateErrorMessage(MethodErrorMeta selectedMethodMeta, MethodScope methodScope) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error102 : in line ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(selectedMethodMeta.namePosition().line());
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(selectedMethodMeta.namePosition().column());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" , method ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(methodScope.getScopeName());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" has been defined already");
        return stringBuilder.toString();
    }

    private ValidationResultModel detectIdenticality(Scope candidateScope, ErrorProneEntity errorProneEntity) throws Exception {
        // first check if scope and meta are MethodScope
        MethodScope candidateMethodScope = (MethodScope)candidateScope;
        MethodErrorMeta selectedMethodMeta = (MethodErrorMeta)errorProneEntity.getErrorProneEntityMeta();
        if (candidateMethodScope == null) {
            throw new ScopeTypeInConsistencyException();
        }
        if (selectedMethodMeta == null) {
            throw new IncorrectDetectorExeception();
        }

        // check if method signature is identical
        if (candidateMethodScope.getScopeName().equals(selectedMethodMeta.methodName()) &&
                candidateMethodScope.getReturnType().equals(selectedMethodMeta.methodReturnType()) &&
                selectedMethodMeta.parameters().size() == candidateMethodScope.getParameters().size()
        ) {
            // check all method parameters if identical
            int areAllParameteresIdentical = 0;
            for (ParameterModel parameter: candidateMethodScope.getParameters()) {
                for (ParameterModel secondParameter: selectedMethodMeta.parameters()) {
                    if (parameter.name().equals(secondParameter.name()) && parameter.type().equals(secondParameter.type())) {
                        areAllParameteresIdentical++;
                    }
                }
            }
            if (areAllParameteresIdentical == candidateMethodScope.getParameters().size()) {
                // methods were identical and redeclared
                return new ValidationResultModel(
                        false,
                        ValidationTypes.methodReDeclaration,
                        errorProneEntity,
                        candidateMethodScope,
                        this.generateErrorMessage(
                                selectedMethodMeta,
                                candidateMethodScope
                        )
                );
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

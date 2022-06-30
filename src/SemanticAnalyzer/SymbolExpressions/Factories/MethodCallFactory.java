package SemanticAnalyzer.SymbolExpressions.Factories;

import Constants.Constants;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.JScope.Scopes.MethodScope;
import SemanticAnalyzer.SymbolExpressions.Fix.MethodCallExpression;
import SemanticAnalyzer.Validators.ValidationResultModel;
import gen.JythonParser;

import java.util.List;
import java.util.Optional;

public class MethodCallFactory {

    private final Optional<String> shouldReturnType;

    public MethodCallFactory(Optional<String> shouldReturnType) {
        this.shouldReturnType = shouldReturnType;
    }

    public ValidationResultModel generateMethodCall(JythonParser.Method_callContext context, List<Scope> scopeList) {

        for (Scope scope: scopeList) {
            if (scope.getScopeType() == ScopeType.classs) {
                for (Scope childScope: scope.getAllChildScopes()) {
                    if (childScope.getScopeType() == ScopeType.method && context.methodName.getText().equals(childScope.getScopeName())) {
                        MethodScope methodScope = (MethodScope) childScope;
                        if (shouldReturnType.isPresent()) {
                            if (shouldReturnType.get().equals(methodScope.getReturnType())){
                                return new ArgsFactory(Optional.of(methodScope.getReturnType())).generateArgsList(
                                        context.args(),
                                        scopeList,
                                        methodScope.getParameters(),
                                        methodScope.getNamePosition(),
                                        methodScope.getScopeName()
                                );
                            } else {
                                return new ValidationResultModel(
                                        false,
                                        null,
                                        null,
                                        null,
                                        this.generateErrorMessageForInvalidReturnType(
                                                context,
                                                methodScope.getReturnType()
                                        )
                                );
                            }
                        } else {
                            return new ArgsFactory(Optional.of(methodScope.getReturnType())).generateArgsList(
                                    context.args(),
                                    scopeList,
                                    methodScope.getParameters(),
                                    methodScope.getNamePosition(),
                                    methodScope.getScopeName()
                            );
                        }
                    }
                }
            }
        }
        return new ValidationResultModel(
                false,
                null,
                null,
                null,
                this.generateErrorMessageForNotFoundMethod(context)
                );
    }

    private String generateErrorMessageForInvalidReturnType(JythonParser.Method_callContext context, String methodScopeReturnType) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error103 : in line ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(context.methodName.getLine());
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(context.methodName.getCharPositionInLine());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" , method ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(context.methodName.getText());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" return type does not match ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(methodScopeReturnType);
        stringBuilder.append(Constants.RightBracket);
        return stringBuilder.toString();
    }

    private String generateErrorMessageForNotFoundMethod(JythonParser.Method_callContext context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error103 : in line ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(context.methodName.getLine());
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(context.methodName.getCharPositionInLine());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" , method ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(context.methodName.getText());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" was not defined");
        return stringBuilder.toString();
    }
}

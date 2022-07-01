package SemanticAnalyzer.Validators.Usage;

import Constants.Constants;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.JScope.Scopes.MethodScope;
import SemanticAnalyzer.Models.ValidationResultModel;
import gen.JythonParser;
import java.util.List;
import java.util.Optional;

public final class ClassInstanceUsageDetector {

    private final Optional<String> shouldReturnType;

    public ClassInstanceUsageDetector(Optional<String> shouldReturnType) {
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
                        return new ArgsUsageDetector(shouldReturnType).generateArgsList(
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
                false,
                null,
                null,
                null,
                this.generateErrorForTooManyARG(context)
        );
    }

    private String generateErrorForTooManyARG(JythonParser.Class_instanceContext context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error106 : in line ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(context.start.getLine());
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(context.start.getCharPositionInLine());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" , class  ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(context.className.getText());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" was not found");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}

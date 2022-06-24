package SemanticAnalyzer.SymbolExpressions.Fix;

import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;
import java.util.ArrayList;
import java.util.List;

public class MethodCallExpression implements FixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final String methodName;

    private List<FixSymbolExpression> args;

    public MethodCallExpression(String methodName) {
        this.args = new ArrayList<>();
        this.methodName = methodName;
        this.expressionKind = SymbolExpressionKind.MethodCallExpression;
    }

    public MethodCallExpression(String methodName, List<FixSymbolExpression> args) {
        this.args = args;
        this.methodName = methodName;
        this.expressionKind = SymbolExpressionKind.MethodCallExpression;
    }

    @Override
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }
}

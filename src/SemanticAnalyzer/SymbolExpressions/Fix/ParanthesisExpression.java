package SemanticAnalyzer.SymbolExpressions.Fix;

import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class ParanthesisExpression implements FixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final FixSymbolExpression expression;

    public ParanthesisExpression(FixSymbolExpression expression) {
        this.expression = expression;
        this.expressionKind = SymbolExpressionKind.ParanthesisExpression;
    }
    @Override
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }
}

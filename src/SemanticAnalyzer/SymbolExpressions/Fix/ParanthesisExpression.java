package SemanticAnalyzer.SymbolExpressions.Fix;

import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class ParanthesisExpression implements FixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final FixSymbolExpression expression;

    public ParanthesisExpression(FixSymbolExpression expression) {
        this.expression = expression;
        this.expressionKind = SymbolExpressionKind.ParanthesisExpression;
    }

    @Override
    public PositionModel getPostion() {
        return null;
    }
    @Override
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }
}

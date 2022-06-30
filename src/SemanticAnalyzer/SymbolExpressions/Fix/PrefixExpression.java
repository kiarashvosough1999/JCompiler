package SemanticAnalyzer.SymbolExpressions.Fix;

import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.PreFix.PreFixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class PrefixExpression implements FixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final PreFixSymbolExpression preFixSymbolExpression;

    public PrefixExpression(PreFixSymbolExpression preFixSymbolExpression) {
        this.preFixSymbolExpression = preFixSymbolExpression;
        this.expressionKind = SymbolExpressionKind.PrefixExpression;
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

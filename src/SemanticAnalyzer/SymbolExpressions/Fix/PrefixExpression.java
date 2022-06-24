package SemanticAnalyzer.SymbolExpressions.Fix;

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
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }
}
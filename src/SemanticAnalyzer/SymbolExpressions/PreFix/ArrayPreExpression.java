package SemanticAnalyzer.SymbolExpressions.PreFix;

import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class ArrayPreExpression implements PreFixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final PreFixSymbolExpression preFixSymbolExpression;

    private final String arrayCount;

    public ArrayPreExpression(String arrayCount, PreFixSymbolExpression preFixSymbolExpression) {
        this.arrayCount = arrayCount;
        this.preFixSymbolExpression = preFixSymbolExpression;
        this.expressionKind = SymbolExpressionKind.ArrayPreExpression;
    }


    @Override
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }
}

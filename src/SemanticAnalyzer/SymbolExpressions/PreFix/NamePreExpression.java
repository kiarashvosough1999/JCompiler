package SemanticAnalyzer.SymbolExpressions.PreFix;

import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class NamePreExpression implements PreFixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final String id;

    public NamePreExpression(String id) {
        this.id = id;
        this.expressionKind = SymbolExpressionKind.NamePreExpression;
    }


    @Override
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }
}

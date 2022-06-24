package SemanticAnalyzer.SymbolExpressions.Fix;

import Constants.Constants;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class FloatExpression implements FixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final String expressionName;

    private final String floatValue;

    public FloatExpression(String floatValue) {
        this.floatValue = floatValue;
        this.expressionKind = SymbolExpressionKind.FloatExpression;
        this.expressionName = Constants.Float;
    }


    @Override
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }
}

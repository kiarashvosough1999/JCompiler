package SemanticAnalyzer.SymbolExpressions.Fix;

import Constants.Constants;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class IntegerExpression implements FixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final String expressionName;

    private final String integerValue;

    public IntegerExpression(String integerValue) {
        this.integerValue = integerValue;
        this.expressionKind = SymbolExpressionKind.IntegerExpression;
        this.expressionName = Constants.Integer;
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

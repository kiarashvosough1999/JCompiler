package SemanticAnalyzer.SymbolExpressions.Fix;

import Constants.Constants;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class BoolExpression implements FixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final String expressionName;

    private final String boolValue;

    public BoolExpression(String boolValue) {
        this.boolValue = boolValue;
        this.expressionKind = SymbolExpressionKind.BoolExpression;
        this.expressionName = Constants.Bool;
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

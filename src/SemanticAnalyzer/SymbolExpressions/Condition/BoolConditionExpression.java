package SemanticAnalyzer.SymbolExpressions.Condition;

import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class BoolConditionExpression implements ConditionSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final String boolValue;

    public BoolConditionExpression(String boolValue) {
        this.boolValue = boolValue;
        expressionKind = SymbolExpressionKind.BoolConditionExpression;
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

package SemanticAnalyzer.SymbolExpressions.Condition;

import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.PreFix.PreFixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class PrefixCondition implements ConditionSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final PreFixSymbolExpression preFixSymbolExpression;

    public PrefixCondition(PreFixSymbolExpression preFixSymbolExpression) {
        this.preFixSymbolExpression = preFixSymbolExpression;
        expressionKind = SymbolExpressionKind.PrefixCondition;
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

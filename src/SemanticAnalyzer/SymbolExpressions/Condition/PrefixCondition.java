package SemanticAnalyzer.SymbolExpressions.Condition;

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
    public SymbolExpressionKind getKind() {
        return null;
    }
}

package SemanticAnalyzer.SymbolExpressions.Condition;

import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.Models.RelationalOperators;
import SemanticAnalyzer.SymbolExpressions.Fix.FixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class RelationalOperatorsCondition implements ConditionSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private FixSymbolExpression beforeFixSymbolExpressionl;

    private final RelationalOperators relationalOperators;

    private FixSymbolExpression afterFixSymbolExpressionl;

    public RelationalOperatorsCondition(FixSymbolExpression beforeFixSymbolExpressionl,
                                        RelationalOperators relationalOperators,
                                        FixSymbolExpression afterFixSymbolExpressionl) {
        this.beforeFixSymbolExpressionl = beforeFixSymbolExpressionl;
        this.relationalOperators = relationalOperators;
        this.afterFixSymbolExpressionl = afterFixSymbolExpressionl;
        expressionKind = SymbolExpressionKind.RelationalOperatorsCondition;
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

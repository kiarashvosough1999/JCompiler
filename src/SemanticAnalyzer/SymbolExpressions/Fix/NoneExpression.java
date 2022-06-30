package SemanticAnalyzer.SymbolExpressions.Fix;

import Constants.Constants;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class NoneExpression implements FixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final String expressionName;

    public NoneExpression() {
        this.expressionKind = SymbolExpressionKind.NoneExpression;
        this.expressionName = Constants.None;
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

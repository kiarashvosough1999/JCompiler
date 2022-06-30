package SemanticAnalyzer.SymbolExpressions.Fix;

import Constants.Constants;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class StringExpression implements FixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final String expressionName;

    private final String stringValue;

    public StringExpression(String stringValue) {
        this.stringValue = stringValue;
        this.expressionKind = SymbolExpressionKind.StringExpression;
        this.expressionName = Constants.String;
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

package SemanticAnalyzer.SymbolExpressions.PreFix.Array;

import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.PreFix.PreFixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

import java.util.Optional;

public class ArrayPreExpression implements PreFixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final PreFixSymbolExpression preFixSymbolExpression;

    private final String arrayCount;

    private final PositionModel positionModel;

    public ArrayPreExpression(PositionModel positionModel, String arrayCount, PreFixSymbolExpression preFixSymbolExpression) {
        this.positionModel = positionModel;
        this.arrayCount = arrayCount;
        this.preFixSymbolExpression = preFixSymbolExpression;
        this.expressionKind = SymbolExpressionKind.ArrayPreExpression;
    }

    @Override
    public Boolean isTerminalID() {
        return false;
    }

    @Override
    public Optional<PreFixSymbolExpression> getPrefixExpression() {
        return Optional.ofNullable(preFixSymbolExpression);
    }

    @Override
    public PositionModel getPostion() {
        return positionModel;
    }

    @Override
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }
}

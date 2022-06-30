package SemanticAnalyzer.SymbolExpressions.PreFix.VariableHierarchy;

import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.PreFix.PreFixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

import java.util.Optional;

public class VariableHierarchyPreExpression implements PreFixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final PreFixSymbolExpression preFixSymbolExpression;

    private final String lastVariableName;

    private final PositionModel positionModel;

    public VariableHierarchyPreExpression(PositionModel positionModel, String lastVariableName, PreFixSymbolExpression preFixSymbolExpression) {
        this.positionModel = positionModel;
        this.lastVariableName = lastVariableName;
        this.preFixSymbolExpression = preFixSymbolExpression;
        this.expressionKind = SymbolExpressionKind.VariableHierarchyPreExpression;
    }

    @Override
    public Optional<PreFixSymbolExpression> getPrefixExpression() {
        return Optional.ofNullable(preFixSymbolExpression);
    }

    @Override
    public Boolean isTerminalID() {
        return false;
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

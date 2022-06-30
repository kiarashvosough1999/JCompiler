package SemanticAnalyzer.SymbolExpressions.PreFix.MethodHierarchy;

import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.Fix.FixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.PreFix.PreFixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MethodHierarchyPreExpression implements PreFixSymbolExpression {
    private final SymbolExpressionKind expressionKind;

    private final PreFixSymbolExpression preFixSymbolExpression;

    private List<FixSymbolExpression> args;

    private final String methodName;

    private final PositionModel positionModel;

    public MethodHierarchyPreExpression(PositionModel positionModel, String methodName, PreFixSymbolExpression preFixSymbolExpression) {
        this.positionModel = positionModel;
        this.args = new ArrayList<>();
        this.methodName = methodName;
        this.preFixSymbolExpression = preFixSymbolExpression;
        this.expressionKind = SymbolExpressionKind.MethodHierarchyPreExpression;
    }

    public MethodHierarchyPreExpression(PositionModel positionModel, String methodName, PreFixSymbolExpression preFixSymbolExpression, List<FixSymbolExpression> args) {
        this.positionModel = positionModel;
        this.args = args;
        this.methodName = methodName;
        this.preFixSymbolExpression = preFixSymbolExpression;
        this.expressionKind = SymbolExpressionKind.MethodHierarchyPreExpression;
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

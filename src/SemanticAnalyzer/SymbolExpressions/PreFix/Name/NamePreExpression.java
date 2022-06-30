package SemanticAnalyzer.SymbolExpressions.PreFix.Name;

import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.PreFix.PreFixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

import java.util.Optional;

public class NamePreExpression implements PreFixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final String id;

    private NamePreExpressionMeta namePreExpressionMeta;

    private final PositionModel positionModel;

    public NamePreExpression(PositionModel positionModel, String id) {
        this.positionModel = positionModel;
        this.id = id;
        this.expressionKind = SymbolExpressionKind.NamePreExpression;
    }

    public String getId() {
        return id;
    }

    @Override
    public Optional<PreFixSymbolExpression> getPrefixExpression() {
        return Optional.empty();
    }

    @Override
    public PositionModel getPostion() {
        return positionModel;
    }

    @Override
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }

    @Override
    public Boolean isTerminalID() {
        return true;
    }

    public NamePreExpressionMeta getNamePreExpressionMeta() {
        return namePreExpressionMeta;
    }

    public void setNamePreExpressionMeta(NamePreExpressionMeta namePreExpressionMeta) {
        this.namePreExpressionMeta = namePreExpressionMeta;
    }
}

package SemanticAnalyzer.SymbolExpressions;

import SemanticAnalyzer.Models.PositionModel;

public interface SymbolExpression {

    PositionModel getPostion();

    SymbolExpressionKind getKind();
}

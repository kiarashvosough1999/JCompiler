package SemanticAnalyzer.SymbolExpressions.PreFix;

import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class VariableHierarchyPreExpression implements PreFixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final PreFixSymbolExpression preFixSymbolExpression;

    private final String lastVariableName;

    public VariableHierarchyPreExpression(String lastVariableName, PreFixSymbolExpression preFixSymbolExpression) {
        this.lastVariableName = lastVariableName;
        this.preFixSymbolExpression = preFixSymbolExpression;
        this.expressionKind = SymbolExpressionKind.VariableHierarchyPreExpression;
    }


    @Override
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }
}

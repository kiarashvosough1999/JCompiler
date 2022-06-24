package SemanticAnalyzer.SymbolExpressions.PreFix;

import SemanticAnalyzer.SymbolExpressions.Fix.FixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

import java.util.ArrayList;
import java.util.List;

public class MethodHierarchyPreExpression implements PreFixSymbolExpression {
    private final SymbolExpressionKind expressionKind;

    private final PreFixSymbolExpression preFixSymbolExpression;

    private List<FixSymbolExpression> args;

    private final String methodName;

    public MethodHierarchyPreExpression(String methodName, PreFixSymbolExpression preFixSymbolExpression) {
        this.args = new ArrayList<>();
        this.methodName = methodName;
        this.preFixSymbolExpression = preFixSymbolExpression;
        this.expressionKind = SymbolExpressionKind.MethodHierarchyPreExpression;
    }

    public MethodHierarchyPreExpression(String methodName, PreFixSymbolExpression preFixSymbolExpression, List<FixSymbolExpression> args) {
        this.args = args;
        this.methodName = methodName;
        this.preFixSymbolExpression = preFixSymbolExpression;
        this.expressionKind = SymbolExpressionKind.MethodHierarchyPreExpression;
    }


    @Override
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }
}

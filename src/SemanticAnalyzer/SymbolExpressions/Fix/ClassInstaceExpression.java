package SemanticAnalyzer.SymbolExpressions.Fix;

import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;
import java.util.ArrayList;
import java.util.List;

public class ClassInstaceExpression implements FixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final String className;

    private List<FixSymbolExpression> args;

    public ClassInstaceExpression(String className) {
        this.args = new ArrayList<>();
        this.className = className;
        this.expressionKind = SymbolExpressionKind.ClassInstaceExpression;
    }

    public ClassInstaceExpression(String className, List<FixSymbolExpression> args) {
        this.args = args;
        this.className = className;
        this.expressionKind = SymbolExpressionKind.ClassInstaceExpression;
    }

    @Override
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }
}

package SemanticAnalyzer.SymbolExpressions.Fix;

import SemanticAnalyzer.Models.ArithmeticOperator;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class ArithmeticExpression implements FixSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final FixSymbolExpression beforeExpression;

    private final ArithmeticOperator arithmeticOperator;

    private final FixSymbolExpression afterExpression;

    public ArithmeticExpression(FixSymbolExpression beforeExpression, ArithmeticOperator arithmeticOperator, FixSymbolExpression afterExpression) {
        this.beforeExpression = beforeExpression;
        this.arithmeticOperator = arithmeticOperator;
        this.afterExpression = afterExpression;
        this.expressionKind = SymbolExpressionKind.ArithmeticExpression;
    }
    @Override
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }
}

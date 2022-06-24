package SemanticAnalyzer.Models;

import SemanticAnalyzer.SymbolExpressions.Condition.ConditionSymbolExpression;

public record ConditionPack(ConditionSymbolExpression conditionSymbolExpression, String beforeOperator) {

    public Boolean hasOperator() {
        return beforeOperator != null;
    }
}

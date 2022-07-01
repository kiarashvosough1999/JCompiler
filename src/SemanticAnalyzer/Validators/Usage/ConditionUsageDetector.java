package SemanticAnalyzer.Validators.Usage;

import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.Models.ValidationResultModel;
import gen.JythonParser;
import java.util.List;
import java.util.Optional;

public final class ConditionUsageDetector {

    public ConditionUsageDetector() {}

    public ValidationResultModel generateConditionSymbolExpression(JythonParser.Condition_listContext context, List<Scope> scopeList) {

        for (JythonParser.ConditionContext conditionContext: context.condition()) {
            if (conditionContext.prefixexp() != null) {
                return new PreFixExpressionUsageDetector(Optional.of("bool"))
                        .generatePreFixSymbolExpression(
                                conditionContext.prefixexp(),
                                scopeList
                        );
            } else if (conditionContext.exp().size() > 0 && conditionContext.relational_operators() != null) {
                for (JythonParser.ExpContext expContext: conditionContext.exp()) {
                    return new ExpressionUsageDetector(Optional.of("bool"))
                            .generateFixSymbolExpression(
                              expContext,
                              scopeList
                            );
                }
            }
        }
        return new ValidationResultModel(
                true,
                null,
                null,
                null,
                ""
        );
    }
}

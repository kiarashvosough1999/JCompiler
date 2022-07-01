package SemanticAnalyzer.Validators.Usage;

import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.Models.ValidationResultModel;
import gen.JythonParser;
import java.util.List;
import java.util.Optional;

public final class AssignmentUsageDetector {

    public AssignmentUsageDetector() {}

    public ValidationResultModel generateAssignmentSymbolExpression(JythonParser.AssignmentContext context, List<Scope> scopes) {

        if (context.prefixexp() != null && context.assignment_operators() != null && context.exp() != null) {

            PreFixExpressionUsageDetector preFixExpressionUsageDetector = new PreFixExpressionUsageDetector(Optional.empty());
            ValidationResultModel first = preFixExpressionUsageDetector
                    .generatePreFixSymbolExpression(
                            context.prefixexp(),
                            scopes
                    );

            if (!first.isValid()) {
                return first;
            }

            ValidationResultModel second = new ExpressionUsageDetector(preFixExpressionUsageDetector.getLeftType())
                    .generateFixSymbolExpression(
                            context.exp(),
                            scopes
                    );
            return second;

        } else if (context.varDec() != null && context.assignment_operators() != null && context.exp() != null) {
            return new ExpressionUsageDetector(Optional.ofNullable(context.varDec().variableType.getText()))
                    .generateFixSymbolExpression(
                            context.exp(),
                            scopes
                    );

        } else if (context.arrayDec() != null && context.class_instance() != null && context.arraySize != null) {

        }
        return null;
    }
}

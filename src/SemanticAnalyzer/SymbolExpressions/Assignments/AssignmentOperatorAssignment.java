package SemanticAnalyzer.SymbolExpressions.Assignments;

import SemanticAnalyzer.Models.AssignmentOperators;
import SemanticAnalyzer.SymbolExpressions.Fix.FixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.PreFix.PreFixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class AssignmentOperatorAssignment implements AssignmentSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final PreFixSymbolExpression preFixSymbolExpression;

    private final AssignmentOperators assignmentOperators;

    private final FixSymbolExpression fixSymbolExpression;

    public AssignmentOperatorAssignment(PreFixSymbolExpression preFixSymbolExpression,
                                        AssignmentOperators assignmentOperators,
                                        FixSymbolExpression fixSymbolExpression) {
        this.preFixSymbolExpression = preFixSymbolExpression;
        this.assignmentOperators = assignmentOperators;
        this.fixSymbolExpression = fixSymbolExpression;

        this.expressionKind = SymbolExpressionKind.ArrayDeclarationAssignment;
    }


    @Override
    public SymbolExpressionKind getKind() {
        return null;
    }
}

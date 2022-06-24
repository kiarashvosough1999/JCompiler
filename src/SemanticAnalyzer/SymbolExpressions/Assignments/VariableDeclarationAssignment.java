package SemanticAnalyzer.SymbolExpressions.Assignments;

import SemanticAnalyzer.Models.AssignmentOperators;
import SemanticAnalyzer.SymbolExpressions.Fix.FixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;
import SemanticAnalyzer.SymbolValues.SymbolValue;

public class VariableDeclarationAssignment implements AssignmentSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final SymbolValue symbolValue;

    private final AssignmentOperators assignmentOperators;

    private final FixSymbolExpression fixSymbolExpression;

    public VariableDeclarationAssignment(SymbolValue symbolValue,
                                         AssignmentOperators assignmentOperators,
                                         FixSymbolExpression fixSymbolExpression) {
        this.symbolValue = symbolValue;
        this.assignmentOperators = assignmentOperators;
        this.fixSymbolExpression = fixSymbolExpression;
        this.expressionKind = SymbolExpressionKind.VariableDeclarationAssignment;
    }


    @Override
    public SymbolExpressionKind getKind() {
        return null;
    }
}

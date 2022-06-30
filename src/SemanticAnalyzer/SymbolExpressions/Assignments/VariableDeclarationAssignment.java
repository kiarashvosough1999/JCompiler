package SemanticAnalyzer.SymbolExpressions.Assignments;

import SemanticAnalyzer.Models.AssignmentOperators;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.Fix.FixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;
import SemanticAnalyzer.SymbolValues.SymbolValue;

public class VariableDeclarationAssignment implements AssignmentSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final SymbolValue symbolValue;

    private final AssignmentOperators assignmentOperators;

    private final FixSymbolExpression fixSymbolExpression;

    private final PositionModel positionModel;

    public VariableDeclarationAssignment(PositionModel positionModel,
                                         SymbolValue symbolValue,
                                         AssignmentOperators assignmentOperators,
                                         FixSymbolExpression fixSymbolExpression) {
        this.positionModel = positionModel;
        this.symbolValue = symbolValue;
        this.assignmentOperators = assignmentOperators;
        this.fixSymbolExpression = fixSymbolExpression;
        this.expressionKind = SymbolExpressionKind.VariableDeclarationAssignment;
    }


    @Override
    public PositionModel getPostion() {
        return positionModel;
    }

    @Override
    public SymbolExpressionKind getKind() {
        return expressionKind;
    }
}

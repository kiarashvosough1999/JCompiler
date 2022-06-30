package SemanticAnalyzer.SymbolExpressions.Assignments;

import SemanticAnalyzer.Models.AssignmentOperators;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.Fix.FixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.PreFix.PreFixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;

public class AssignmentOperatorAssignment implements AssignmentSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final PreFixSymbolExpression leftPrefixSymbolExpression;

    private final AssignmentOperators assignmentOperators;

    private final FixSymbolExpression rightFixSymbolExpression;

    private final PositionModel positionModel;

    public AssignmentOperatorAssignment(PositionModel positionModel,
                                        PreFixSymbolExpression leftPrefixSymbolExpression,
                                        AssignmentOperators assignmentOperators,
                                        FixSymbolExpression rightFixSymbolExpression) {
        this.positionModel = positionModel;
        this.leftPrefixSymbolExpression = leftPrefixSymbolExpression;
        this.assignmentOperators = assignmentOperators;
        this.rightFixSymbolExpression = rightFixSymbolExpression;
        this.expressionKind = SymbolExpressionKind.AssignmentOperatorAssignment;
    }


    public PreFixSymbolExpression getLeftPrefixSymbolExpression() {
        return leftPrefixSymbolExpression;
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

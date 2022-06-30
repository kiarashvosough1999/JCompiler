package SemanticAnalyzer.SymbolExpressions.Assignments;

import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.Fix.ClassInstaceExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import SemanticAnalyzer.SymbolValues.Values.ArrayFieldValue;

public class ArrayDeclarationAssignment implements AssignmentSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final ArrayFieldValue arrayFieldValue;

    private final ClassInstaceExpression classInstaceExpression;

    private final String arraySize;

    private final PositionModel positionModel;

    public ArrayDeclarationAssignment(PositionModel positionModel,
                                      ArrayFieldValue arrayFieldValue,
                                      ClassInstaceExpression classInstaceExpression,
                                      String arraySize) {
        this.positionModel = positionModel;
        this.arrayFieldValue = arrayFieldValue;
        this.classInstaceExpression = classInstaceExpression;
        this.arraySize = arraySize;
        this.expressionKind = SymbolExpressionKind.ArrayDeclarationAssignment;
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

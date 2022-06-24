package SemanticAnalyzer.SymbolExpressions.Assignments;

import SemanticAnalyzer.SymbolExpressions.Fix.ClassInstaceExpression;
import SemanticAnalyzer.SymbolExpressions.SymbolExpressionKind;
import SemanticAnalyzer.SymbolValues.SymbolValue;

public class ArrayDeclarationAssignment implements AssignmentSymbolExpression {

    private final SymbolExpressionKind expressionKind;

    private final SymbolValue symbolValue;

    private final ClassInstaceExpression classInstaceExpression;

    private final String arraySize;

    public ArrayDeclarationAssignment(SymbolValue symbolValue,
                                      ClassInstaceExpression classInstaceExpression,
                                      String arraySize) {
        this.symbolValue = symbolValue;
        this.classInstaceExpression = classInstaceExpression;
        this.arraySize = arraySize;
        this.expressionKind = SymbolExpressionKind.ArrayDeclarationAssignment;
    }

    @Override
    public SymbolExpressionKind getKind() {
        return null;
    }
}

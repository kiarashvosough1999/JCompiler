package SemanticAnalyzer.SymbolExpressions.Factories;

import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.Models.AssignmentOperators;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.Assignments.ArrayDeclarationAssignment;
import SemanticAnalyzer.SymbolExpressions.Assignments.AssignmentOperatorAssignment;
import SemanticAnalyzer.SymbolExpressions.Assignments.AssignmentSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.Assignments.VariableDeclarationAssignment;
import SemanticAnalyzer.SymbolExpressions.Fix.ClassInstaceExpression;
import SemanticAnalyzer.SymbolValues.SymbolValueOwner;
import SemanticAnalyzer.SymbolValues.Values.ArrayFieldValue;
import SemanticAnalyzer.SymbolValues.Values.FieldValue;
import gen.JythonParser;
import java.util.Stack;

//public class AssignmentFactory {
//
//    public AssignmentSymbolExpression generateAssignmentSymbolExpression(JythonParser.AssignmentContext context, Stack<Scope> scopes) {
//        if (context.prefixexp() != null && context.assignment_operators() != null && context.exp() != null) {
//            AssignmentOperatorAssignment ass = new AssignmentOperatorAssignment(
//                    new PositionModel(
//                            context.start.getLine(),
//                            context.start.getCharPositionInLine()
//                    ),
//                    new PreFixExpressionFactory().generatePreFixSymbolExpression(context.prefixexp()),
//                    AssignmentOperators.from(context.assignment_operators().getText()),
//                    new ExpressionFactory().generateFixSymbolExpression(context.exp())
//            );
//            return new AssignmentOperatorAssignment(
//                    new PositionModel(
//                            context.start.getLine(),
//                            context.start.getCharPositionInLine()
//                    ),
//                    new PreFixExpressionFactory().generatePreFixSymbolExpression(context.prefixexp()),
//                    AssignmentOperators.from(context.assignment_operators().getText()),
//                    new ExpressionFactory().generateFixSymbolExpression(context.exp())
//            );
//        } else if (context.varDec() != null && context.assignment_operators() != null && context.exp() != null) {
//            return new VariableDeclarationAssignment(
//                    new PositionModel(
//                            context.start.getLine(),
//                            context.start.getCharPositionInLine()
//                    ),
//                    new FieldValue(
//                            context.varDec().varibaleName.getText(),
//                            context.varDec().variableType.getText(),
//                            new PositionModel(
//                                    context.varDec().varibaleName.getLine(),
//                                    context.varDec().varibaleName.getCharPositionInLine()
//                            ),
//                            new PositionModel(
//                                    context.varDec().variableType.getLine(),
//                                    context.varDec().variableType.getCharPositionInLine()
//                            ),
//                            SymbolValueOwner.from(scopes.peek().getScopeType())
//                    )
//                    ,
//                    AssignmentOperators.from(context.assignment_operators().getText()),
//                    new ExpressionFactory().generateFixSymbolExpression(context.exp())
//            );
//        } else if (context.arrayDec() != null && context.class_instance() != null && context.arraySize != null) {
//            return new ArrayDeclarationAssignment(
//                    new PositionModel(
//                            context.start.getLine(),
//                            context.start.getCharPositionInLine()
//                    ),
//                    new ArrayFieldValue(
//                            context.arrayDec().arrayVaribaleName.getText(),
//                            context.arrayDec().arrayType.getText(),
//                            context.arrayDec().arraySize.getText(),
//                            new PositionModel(
//                                    context.arrayDec().arrayVaribaleName.getLine(),
//                                    context.arrayDec().arrayVaribaleName.getCharPositionInLine()
//                            ),
//                            new PositionModel(
//                                    context.arrayDec().arrayType.getLine(),
//                                    context.arrayDec().arrayType.getCharPositionInLine()
//                            ),
//                            SymbolValueOwner.from(scopes.peek().getScopeType())
//                    )
//                    ,
//                    new ClassInstaceExpression(
//                            context.class_instance().className.getText(),
//                            new ArgsFactory().generateArgsList(context.class_instance().args())
//                    ),
//                    context.arraySize.getText()
//            );
//        }
//        return null;
//    }
//}

package SemanticAnalyzer.SymbolExpressions.Factories;

import SemanticAnalyzer.Models.ArithmeticOperator;
import SemanticAnalyzer.SymbolExpressions.Fix.*;
import gen.JythonParser;
import java.util.List;

//public class ExpressionFactory {
//
//
//    public FixSymbolExpression generateFixSymbolExpression (JythonParser.ExpContext context) {
//
//        if (context.BOOL() != null) {
//            return new BoolExpression(
//                    context.BOOL().getText()
//            );
//        } else if (context.INTEGER() != null) {
//            return new IntegerExpression(
//                    context.INTEGER().getText()
//            );
//        } else if (context.STRING() != null) {
//            return new StringExpression(
//                    context.STRING().getText()
//            );
//        } else if (context.FLOAT() != null) {
//            return new FloatExpression(
//                    context.FLOAT().getText()
//            );
//        } else if (context.prefixexp() != null) {
//            return new PrefixExpression(
//                    new PreFixExpressionFactory().generatePreFixSymbolExpression(context.prefixexp())
//            );
//        } else if (context.exp().size() >= 1 && context.arithmetic_operator() != null) {
//            List<FixSymbolExpression> list = context
//                    .exp()
//                    .parallelStream()
//                    .map(childContext -> new ExpressionFactory().generateFixSymbolExpression(childContext))
//                    .toList();
//            if (list.size() == 1) {
//                return new ArithmeticExpression(
//                        list.get(0),
//                        ArithmeticOperator.from(context.arithmetic_operator().getText()),
//                        null
//                );
//            } else if (list.size() == 2) {
//                return new ArithmeticExpression(
//                        list.get(0),
//                        ArithmeticOperator.from(context.arithmetic_operator().getText()),
//                        list.get(1)
//                );
//            } else {
//                // invalid state
//                return new ArithmeticExpression(
//                        null,
//                        ArithmeticOperator.from(context.arithmetic_operator().getText()),
//                        null
//                );
//            }
//        } else if (context.class_instance() != null) {
//            return new ClassInstaceExpression(
//                    context.class_instance().className.getText(),
//                    new ArgsFactory().generateArgsList(context.class_instance().args())
//            );
//        } else if (context.exp().size() == 1) {
//            return new ParanthesisExpression(
//                    new ExpressionFactory().generateFixSymbolExpression(context.exp().get(1))
//            );
//        } else if (context.methodName != null && context.args() != null) {
//            return new MethodCallExpression(
//                    context.methodName.getText(),
//                    new ArgsFactory().generateArgsList(context.args())
//            );
//        } else {
//            return new NoneExpression();
//        }
//    }
//}

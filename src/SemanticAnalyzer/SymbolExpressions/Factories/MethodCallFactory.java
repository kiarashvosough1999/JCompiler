package SemanticAnalyzer.SymbolExpressions.Factories;

import SemanticAnalyzer.SymbolExpressions.Fix.MethodCallExpression;
import gen.JythonParser;

public class MethodCallFactory {

    public MethodCallExpression generateMethodCall(JythonParser.Method_callContext context) {
        return new MethodCallExpression(
                context.methodName.getText(),
                new ArgsFactory().generateArgsList(context.args())
        );
    }
}

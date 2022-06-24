package SemanticAnalyzer.SymbolExpressions.Factories;

import SemanticAnalyzer.SymbolExpressions.Fix.FixSymbolExpression;
import gen.JythonParser;
import java.util.List;

public class ArgsFactory {

    public List<FixSymbolExpression> generateArgsList(JythonParser.ArgsContext context) {
        return context
                .explist()
                .exp()
                .parallelStream()
                .map( childContext -> new ExpressionFactory().generateFixSymbolExpression(childContext))
                .toList();

    }

}

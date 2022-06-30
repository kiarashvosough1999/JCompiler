package SemanticAnalyzer.SymbolExpressions.Factories;

import SemanticAnalyzer.SymbolExpressions.Fix.FixSymbolExpression;
import gen.JythonParser;
import java.util.List;
import java.util.Optional;

//public class ReturnFactory {
//
//    public Optional<FixSymbolExpression> generateFixSymbolExpression (List<JythonParser.StatementContext> contextList) {
//        try {
//            return Optional.of(
//                    contextList.parallelStream()
//                            .filter(s -> s.return_statment() != null)
//                            .map(JythonParser.StatementContext::return_statment)
//                            .map(s -> new ExpressionFactory().generateFixSymbolExpression(s.exp()))
//                            .toList()
//                            .get(0)
//            );
//        } catch (IndexOutOfBoundsException exception) {
//            return Optional.empty();
//        }
//    }
//}

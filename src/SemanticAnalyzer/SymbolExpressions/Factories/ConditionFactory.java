package SemanticAnalyzer.SymbolExpressions.Factories;

import SemanticAnalyzer.Models.ConditionPack;
import SemanticAnalyzer.Models.RelationalOperators;
import SemanticAnalyzer.SymbolExpressions.Condition.BoolConditionExpression;
import SemanticAnalyzer.SymbolExpressions.Condition.PrefixCondition;
import SemanticAnalyzer.SymbolExpressions.Condition.RelationalOperatorsCondition;
import gen.JythonParser;
import java.util.List;
import java.util.Objects;

//public class ConditionFactory {
//
//    public List<ConditionPack> generateConditionSymbolExpression(JythonParser.Condition_listContext context) {
//        return context.condition()
//                .parallelStream()
//                .map( s -> {
//                    if (s.BOOL() != null) {
//                        return new ConditionPack(
//                                new BoolConditionExpression(
//                                        s.BOOL().getText()
//                                ),
//                                context.operator != null ? context.operator.getText() : null
//                        );
//                    } else if (s.prefixexp() != null) {
//                        return new ConditionPack(
//                                new PrefixCondition(
//                                        new PreFixExpressionFactory().generatePreFixSymbolExpression(s.prefixexp())
//                                ),
//                                context.operator != null ? context.operator.getText() : null
//                        );
//                    } else  if (s.exp().size() > 0 && s.relational_operators() != null){
//                        return new ConditionPack(
//                                new RelationalOperatorsCondition(
//                                        s.exp().size() >= 1 ? new ExpressionFactory().generateFixSymbolExpression(s.exp().get(0)) : null,
//                                        RelationalOperators.from(s.relational_operators().getText()),
//                                        s.exp().size() >= 2 ? new ExpressionFactory().generateFixSymbolExpression(s.exp().get(1)) : null
//                                ),
//                                context.operator != null ? context.operator.getText() : null
//                        );
//                    }
//                    return null;
//                })
//                .filter(Objects::nonNull)
//                .toList();
//    }
//
//}

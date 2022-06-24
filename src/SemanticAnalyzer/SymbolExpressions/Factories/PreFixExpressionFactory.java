package SemanticAnalyzer.SymbolExpressions.Factories;

import SemanticAnalyzer.SymbolExpressions.PreFix.*;
import gen.JythonParser;

public class PreFixExpressionFactory {

    public PreFixExpressionFactory() {}

    public PreFixSymbolExpression generatePreFixSymbolExpression(JythonParser.PrefixexpContext prefixexpContext) {

        if (prefixexpContext.ID() != null) {
            return new NamePreExpression(
                    prefixexpContext.id.getText()
            );
        } else if (prefixexpContext.prefixexp() != null && prefixexpContext.arrayCount != null) {
            return new ArrayPreExpression(
                    prefixexpContext.arrayCount.getText(),
                    this.generatePreFixSymbolExpression(prefixexpContext.prefixexp()) // recursively find expressions
            );
        } else if (prefixexpContext.prefixexp() != null && prefixexpContext.nextId != null) {
            return new VariableHierarchyPreExpression(
                    prefixexpContext.nextId.getText(),
                    this.generatePreFixSymbolExpression(prefixexpContext.prefixexp()) // recursively find expressions
            );
        } else if (prefixexpContext.prefixexp() != null && prefixexpContext.methodNAme != null && prefixexpContext.args() != null) {
            return new MethodHierarchyPreExpression(
                    prefixexpContext.methodNAme.getText(),
                    this.generatePreFixSymbolExpression(prefixexpContext.prefixexp()), // recursively find expressions
                    new ArgsFactory().generateArgsList(prefixexpContext.args())
            );
        }

        return null;
    }
}

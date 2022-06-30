package SemanticAnalyzer.SymbolExpressions.PreFix;

import SemanticAnalyzer.SymbolExpressions.SymbolExpression;
import java.util.Optional;

public interface PreFixSymbolExpression extends SymbolExpression {

    Boolean isTerminalID();

    Optional<PreFixSymbolExpression> getPrefixExpression();

}

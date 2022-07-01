package SemanticAnalyzer.Validators.Usage;

import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.Models.ValidationResultModel;
import gen.JythonParser;
import java.util.List;
import java.util.Optional;

public final class ReturnUsageDetector {

    private final Optional<String> shouldReturnType;

    public ReturnUsageDetector(Optional<String> shouldReturnType) {
        this.shouldReturnType = shouldReturnType;
    }

    public ValidationResultModel generateFixSymbolExpression (JythonParser.Return_statmentContext contextList, List<Scope> scopeList) {
        return new ExpressionUsageDetector(this.shouldReturnType)
                .generateFixSymbolExpression(
                        contextList.exp(),
                        scopeList
                );
    }
}

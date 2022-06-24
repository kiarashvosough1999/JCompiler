package SemanticAnalyzer.Models;

import SemanticAnalyzer.SymbolExpressions.Fix.FixSymbolExpression;

import java.util.Optional;

public record MethodReturnModel(Optional<FixSymbolExpression> expression) { }

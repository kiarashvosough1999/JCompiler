package SemanticAnalyzer.SymbolExpressions.PreFix.Name;

import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.SymbolValues.SymbolValue;

import java.util.Optional;

public record NamePreExpressionMeta(Scope originScope,
                                    Optional<SymbolValue> originSymbolValue,
                                    String type,
                                    Boolean insideMethodParaneters,
                                    Boolean insideParentClass,
                                    Boolean isSelf) { }

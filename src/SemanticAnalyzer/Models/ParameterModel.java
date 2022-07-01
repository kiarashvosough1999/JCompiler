package SemanticAnalyzer.Models;

import SemanticAnalyzer.SymbolValues.SymbolValue;
import SemanticAnalyzer.SymbolValues.SymbolValueOwner;
import SemanticAnalyzer.SymbolValues.Values.FieldValue;

public record ParameterModel(String name, String type, PositionModel namePosition, PositionModel typePosition) {
    public SymbolValue getSymbolValue() {
        return new FieldValue(
                name,
                type,
                namePosition,
                typePosition,
                SymbolValueOwner.method
        );
    }
}

package SemanticAnalyzer.Models;

public record FieldErrorMeta(
        String name,
        String type,
        boolean isArray,
        String arraySize,
        PositionModel namePosition,
        PositionModel typePosition
) {
}

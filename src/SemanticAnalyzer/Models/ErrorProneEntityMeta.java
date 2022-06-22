package SemanticAnalyzer.Models;

import SemanticAnalyzer.Models.PositionModel;

public record ErrorProneEntityMeta(
        PositionModel namePosition,
        PositionModel typePosition
) { }

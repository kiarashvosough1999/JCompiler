package SemanticAnalyzer.Models;

import java.util.List;

public record ClassErrorMeta(String className, PositionModel namePosition, List<ParentClassModel> classParents) {
}

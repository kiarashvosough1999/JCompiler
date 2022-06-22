package SemanticAnalyzer.Models;

import SemanticAnalyzer.Models.ParameterModel;
import SemanticAnalyzer.Models.PositionModel;

import java.util.ArrayList;

public record MethodErrorMeta(
        String methodName,
        String methodReturnType,
        ArrayList<ParameterModel> parameters,
        PositionModel namePosition,
        PositionModel typePosition) {
}

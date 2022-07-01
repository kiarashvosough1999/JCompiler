package SemanticAnalyzer.Models;

import SemanticAnalyzer.Validators.ErrorProneEntity;
import SemanticAnalyzer.Validators.ValidationTypes;

public record ValidationResultModel(
        Boolean isValid,
        ValidationTypes validationTypes,
        ErrorProneEntity errorEntity,
        ErrorProneEntity conflictedErrorEntity,
        String errorMessage
) {



    @Override
    public String toString() {
        return this.errorMessage;
    }
}

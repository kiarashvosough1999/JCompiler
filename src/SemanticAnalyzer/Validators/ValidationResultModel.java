package SemanticAnalyzer.Validators;

public record ValidationResultModel(
        boolean isValid,
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

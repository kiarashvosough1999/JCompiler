package SemanticAnalyzer.Errors;

public record ValidationResultModel(
        boolean isValid,
        ErrorTypes errorTypes,
        ErrorProneEntity errorEntity,
        ErrorProneEntity conflictedErrorEntity,
        String errorMessage
) {
    @Override
    public String toString() {
        return this.errorMessage;
    }
}

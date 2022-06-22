package SemanticAnalyzer.Errors;

import Constants.Constants;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.Models.FieldErrorMeta;
import SemanticAnalyzer.SymbolValues.ArraySymbolValue;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import java.util.List;

public class RedundantPropertyStrategy implements ErrorStrategy {

    private final List<Scope> scopes;

    public RedundantPropertyStrategy(List<Scope> scopes) {
        this.scopes = scopes;
    }

    @Override
    public ValidationResultModel checkValidity(ErrorProneEntity errorProneEntity) throws Exception {
        FieldErrorMeta fieldErrorMeta = (FieldErrorMeta)errorProneEntity.getErrorProneEntityMeta();
        for (Scope scope: this.scopes) {
            // find property on this scope if available
            SymbolValue resolvedSymbolValue = scope.getSymbolTable().getSymbolValueFor(fieldErrorMeta.name());

            if (resolvedSymbolValue != null) {
                boolean areTypesSame = resolvedSymbolValue.getType().equals(fieldErrorMeta.type());
                boolean areNamesSame = resolvedSymbolValue.getName().equals(fieldErrorMeta.name());
                boolean areArraySizeSame = true;

                if (resolvedSymbolValue.isArray() && fieldErrorMeta.isArray()) {
                    ArraySymbolValue resolvedArraySymbolValue = (ArraySymbolValue)resolvedSymbolValue;
                    areArraySizeSame = resolvedArraySymbolValue.getArraySize().equals(fieldErrorMeta.arraySize());
                }

                if (areTypesSame && areNamesSame && areArraySizeSame) {
                    return new ValidationResultModel(
                            false,
                            ErrorTypes.propertyReDeclaration,
                            errorProneEntity,
                            (ErrorProneEntity) resolvedSymbolValue,
                            this.generateErrorMessage(fieldErrorMeta)
                    );
                }
            }
        }
        return new ValidationResultModel(
                true,
                null,
                null,
                null,
                null
        );
    }

    private String generateErrorMessage(FieldErrorMeta fieldErrorMeta) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error103 : in line ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(fieldErrorMeta.namePosition().line());
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(fieldErrorMeta.namePosition().column());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" , field ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(fieldErrorMeta.name());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" has been defined already");
        return stringBuilder.toString();
    }
}

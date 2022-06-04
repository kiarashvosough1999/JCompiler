package SemanticAnalyzer.SymbolValues.Values;

import Constants.Constants;
import SemanticAnalyzer.SymbolValues.SymbolValueKind;
import SemanticAnalyzer.SymbolValues.TypedSymbolValueImpl;

public class ClassFieldValue extends TypedSymbolValueImpl {

    final private String arraySize;

    final private SymbolValueKind kind;

    public ClassFieldValue(String name, String type, String arraySize) {
        super(name, type);
        this.kind = arraySize == null ? SymbolValueKind.classField : SymbolValueKind.classArrayField;
        this.arraySize = arraySize;
    }

    @Override
    public SymbolValueKind getKind() {
        return this.kind;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.VerticalLine);
        stringBuilder.append(Constants.VALUE);
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(this.kind);
        stringBuilder.append(Constants.LeftParan);
        stringBuilder.append(Constants.Name);
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(this.name);
        stringBuilder.append(Constants.RightParan);

        // type

        stringBuilder.append(Constants.LeftParan);
        stringBuilder.append(Constants.TYPE);
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(Constants.LeftBracket);

        if (isPrimitiveType()) {
            stringBuilder.append(type);
        } else {
            stringBuilder.append(Constants.ClassTyped);
            stringBuilder.append(Constants.Equal);
            stringBuilder.append(type);
        }
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(Constants.RightParan);
        return stringBuilder.toString();
    }
}

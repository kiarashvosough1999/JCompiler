package SemanticAnalyzer.SymbolValues.Values;

import Constants.Constants;
import SemanticAnalyzer.Helper;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import SemanticAnalyzer.SymbolValues.SymbolValueKind;

public class ClassFieldValue implements SymbolValue {

    final private String name;

    final private String type;

    final private SymbolValueKind kind;

    public ClassFieldValue(String name, String type) {
        this.type = type;
        this.name = name;
        this.kind = SymbolValueKind.classField;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public Boolean isDefined() {
        return null;
    }

    @Override
    public SymbolValueKind getKind() {
        return this.kind;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getReturnType() {
        return null;
    }

    @Override
    public String getParameters() {
        return null;
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

        if (Helper.isPrimitiveType(type)) {
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
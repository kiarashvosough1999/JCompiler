package SemanticAnalyzer.SymbolValues.Values;

import Constants.Constants;
import SemanticAnalyzer.Errors.ErrorProneNameType;
import SemanticAnalyzer.Helper;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import SemanticAnalyzer.SymbolValues.SymbolValueKind;

public class ClassArrayFieldValue implements SymbolValue, ErrorProneNameType {

    final private String name;

    final private String type;

    final private String arraySize;

    final private SymbolValueKind kind;

    private final PositionModel namePosition;

    private final PositionModel typePosition;

    public ClassArrayFieldValue(String name, String type, String arraySize, PositionModel namePosition, PositionModel typePosition) {
        this.type = type;
        this.name = name;
        this.namePosition = namePosition;
        this.typePosition = typePosition;
        this.kind = SymbolValueKind.classArrayField;
        this.arraySize = arraySize;
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

    @Override
    public PositionModel getNamePosition() {
        return this.namePosition;
    }

    @Override
    public PositionModel getTypePosition() {
        return this.typePosition;
    }
}

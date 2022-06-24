package SemanticAnalyzer.SymbolValues.Values;

import Constants.Constants;
import SemanticAnalyzer.Validators.ErrorProneEntity;
import SemanticAnalyzer.Helper;
import SemanticAnalyzer.Models.FieldErrorMeta;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import SemanticAnalyzer.SymbolValues.SymbolValueKind;
import SemanticAnalyzer.SymbolValues.SymbolValueOwner;

public class FieldValue implements SymbolValue, ErrorProneEntity {

    final private String name;

    final private String type;

    final private SymbolValueKind kind;

    private final PositionModel namePosition;

    private final PositionModel typePosition;

    private final SymbolValueOwner owner;

    public FieldValue(String name, String type, PositionModel namePosition, PositionModel typePosition, SymbolValueOwner owner) {
        this.type = type;
        this.name = name;
        this.namePosition = namePosition;
        this.typePosition = typePosition;
        this.owner = owner;
        this.kind = SymbolValueKind.field;
    }

    @Override
    public SymbolValueOwner getOwner() {
        return owner;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getKeyStarter() {
        return Constants.Field;
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
        return type;
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
        stringBuilder.append(this.owner);
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
    public Boolean isArray() {
        return false;
    }

    @Override
    public Object getErrorProneEntityMeta() {
        return new FieldErrorMeta(
                this.name,
                this.type,
                false,
                null,
                this.namePosition,
                this.typePosition
        );
    }
}

package SemanticAnalyzer.SymbolValues.Values;

import Constants.Constants;
import SemanticAnalyzer.Errors.ErrorProneNameType;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import SemanticAnalyzer.SymbolValues.SymbolValueKind;

import java.util.ArrayList;

public class ClasssValue extends Object implements SymbolValue, ErrorProneNameType {

    final private String name;

    final private SymbolValueKind kind;

    final private ArrayList<String> classParents;

    private final PositionModel namePosition;

    private final PositionModel typePosition;

    public ClasssValue(String name, ArrayList<String> classParents, PositionModel namePosition, PositionModel typePosition) {
        this.name = name;
        this.namePosition = namePosition;
        this.typePosition = typePosition;
        this.kind = SymbolValueKind.classs;
        this.classParents = classParents;
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

        stringBuilder.append(Constants.LeftParan);
        stringBuilder.append(Constants.Parent);
        stringBuilder.append(Constants.Colon);
        for (String str: this.classParents) {
            stringBuilder.append(str);
            stringBuilder.append(Constants.Comma);
        }
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

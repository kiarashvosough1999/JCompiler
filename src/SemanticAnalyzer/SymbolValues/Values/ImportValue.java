package SemanticAnalyzer.SymbolValues.Values;

import Constants.Constants;
import SemanticAnalyzer.Errors.ErrorProneOnName;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import SemanticAnalyzer.SymbolValues.SymbolValueKind;

public class ImportValue extends Object implements SymbolValue, ErrorProneOnName {

    final private String name;
    final private SymbolValueKind kind;

    private final PositionModel namePosition;


    public ImportValue(String name, PositionModel namePosition) {
        this.name = name;
        this.namePosition = namePosition;
        this.kind = SymbolValueKind.importt;
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
        return Constants.VerticalLine +
                Constants.VALUE +
                Constants.Colon +
                this.kind +
                Constants.LeftParan +
                Constants.Name +
                Constants.Colon +
                this.name +
                Constants.RightParan;
    }

    @Override
    public PositionModel getNamePosition() {
        return this.namePosition;
    }
}

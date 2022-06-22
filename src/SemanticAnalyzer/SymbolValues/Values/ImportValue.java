package SemanticAnalyzer.SymbolValues.Values;

import Constants.Constants;
import SemanticAnalyzer.Errors.ErrorProneEntity;
import SemanticAnalyzer.Models.ErrorProneEntityMeta;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import SemanticAnalyzer.SymbolValues.SymbolValueKind;
import SemanticAnalyzer.SymbolValues.SymbolValueOwner;

public class ImportValue extends Object implements SymbolValue, ErrorProneEntity {

    final private String name;

    final private SymbolValueKind kind;

    private final PositionModel namePosition;

    private final SymbolValueOwner owner;

    public ImportValue(String name, PositionModel namePosition, SymbolValueOwner owner) {
        this.name = name;
        this.namePosition = namePosition;
        this.owner = owner;
        this.kind = SymbolValueKind.importt;
    }

    @Override
    public SymbolValueOwner getOwner() {
        return owner;
    }

    @Override
    public String getKeyStarter() {
        return SymbolValueKind.importt.toString();
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
    public Boolean isArray() {
        return false;
    }
    @Override
    public Object getErrorProneEntityMeta() {
        return new ErrorProneEntityMeta(
                this.namePosition,
                null
        );
    }
}

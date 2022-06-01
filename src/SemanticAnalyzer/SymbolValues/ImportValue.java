package SemanticAnalyzer.SymbolValues;

import Constants.Constants;
import SemanticAnalyzer.SymbolValue;
import SemanticAnalyzer.SymbolValueKind;

public class ImportValue extends Object implements SymbolValue {

    final private String name;
    final private SymbolValueKind kind;

    public ImportValue(String name) {
        this.name = name;
        this.kind = SymbolValueKind.importt;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getValue() {
        return Constants.VALUE +
                Constants.Colon +
                this.kind +
                Constants.LeftParan +
                Constants.Name +
                Constants.Colon +
                this.name +
                Constants.RightParan;
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
        return Constants.VerticalLine + getValue();
    }
}

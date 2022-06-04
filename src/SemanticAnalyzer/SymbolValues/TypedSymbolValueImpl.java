package SemanticAnalyzer.SymbolValues;

public class TypedSymbolValueImpl extends Object implements TypedSymbolValue {

    final protected String name;

    final protected String type;

    public TypedSymbolValueImpl(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return this.name;
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
        return null;
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
    public Boolean isPrimitiveType() {
        return switch (this.type) {
            case "int", "bool", "float", "str" -> true;
            default -> false;
        };
    }
}

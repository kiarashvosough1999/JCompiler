package SemanticAnalyzer.SymbolValues;

public enum SymbolValueKind {

    importt("Import"),
    classs("Class"),

    field("Field"),

    arrayField("ArrayField"),

    constructor("Constructor"),

    method("Method");

    private final String name;

    SymbolValueKind(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

package SemanticAnalyzer;

public enum SymbolValueKind {

    importt("Import"),
    classs("Class"),
    classField("ClassField"),
    classArrayField("classArrayField"),
    field("Field"),
    constructor("Constructor"),
    method("Method"),
    methodField("MethodField"),
    blockField("BlockField");



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

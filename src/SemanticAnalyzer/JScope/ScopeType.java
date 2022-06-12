package SemanticAnalyzer.JScope;

public enum ScopeType {

    program("program"),

    classs("class"),
    method("method"),

    constructor("constructor"),

    block("block");

    private final String name;
    ScopeType(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

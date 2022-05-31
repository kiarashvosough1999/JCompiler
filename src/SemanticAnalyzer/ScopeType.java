package SemanticAnalyzer;

public enum ScopeType {

    program("program"),
    classs("class"),
    method("method"),
    iff("if"),
    elsif("elseif"),
    elses("else"),
    forr("for"),
    whilee("while");



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

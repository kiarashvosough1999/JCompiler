package Indentation;

public enum IndentationType {

    program("program"),
    importt("import"),
    classs("class"),
    variable("variable"),
    array("array"),
    concstructor("concstructor"),
    parameters("parameters"),
    method("method"),
    statement("statement")
    ;

    private final String name;
    IndentationType(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

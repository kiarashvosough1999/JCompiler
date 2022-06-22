package SemanticAnalyzer.Errors;

public enum ErrorTypes {

    methodReDeclaration("MethodReDeclaration"),

    propertyReDeclaration("PropertyReDeclaration"),

    unDefiniedProperty("UnDefiniedProperty"),

    unDefiniedClass("UnDefiniedClass");

    private final String name;

    ErrorTypes(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

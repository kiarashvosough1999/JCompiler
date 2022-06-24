package SemanticAnalyzer.Validators;

public enum ValidationTypes {

    methodReDeclaration("MethodReDeclaration"),

    propertyReDeclaration("PropertyReDeclaration"),

    unDefiniedProperty("UnDefiniedProperty"),

    unDefiniedClass("UnDefiniedClass");

    private final String name;

    ValidationTypes(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

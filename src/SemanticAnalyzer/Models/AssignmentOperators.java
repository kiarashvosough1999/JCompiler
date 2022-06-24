package SemanticAnalyzer.Models;

public enum AssignmentOperators {

    Assign("="),

    PlusAssign("+="),

    MinusAssign("-="),

    MultiplyAssign("*="),

    DivisionAssign("/=");

    private final String name;

    AssignmentOperators(String name) {
        this.name = name;
    }

    public static AssignmentOperators from(String name) {
        switch (name) {
            case "=" -> {
                return AssignmentOperators.Assign;
            }
            case "+=" -> {
                return AssignmentOperators.PlusAssign;
            }
            case "-=" -> {
                return AssignmentOperators.MinusAssign;
            }
            case "*=" -> {
                return AssignmentOperators.MultiplyAssign;
            }
            case "/=" -> {
                return AssignmentOperators.DivisionAssign;
            }
        }
        return null;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

}

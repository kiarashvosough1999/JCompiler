package SemanticAnalyzer.Models;

public enum ArithmeticOperator {

    Plus("+"),

    Minus("-"),

    Multiply("*"),

    Division("/"),

    Mod("%");

    private final String name;

    ArithmeticOperator(String name) {
        this.name = name;
    }

    public static ArithmeticOperator from(String name) {
        switch (name) {
            case "+" -> {
                return ArithmeticOperator.Plus;
            }
            case "-" -> {
                return ArithmeticOperator.Minus;
            }
            case "*" -> {
                return ArithmeticOperator.Multiply;
            }
            case "/" -> {
                return ArithmeticOperator.Division;
            }
            case "%" -> {
                return ArithmeticOperator.Mod;
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

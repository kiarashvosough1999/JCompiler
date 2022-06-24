package SemanticAnalyzer.Models;

public enum RelationalOperators {

    SmallerThan("<"),

    GreaterThan(">"),

    SmallerThanOrEqual("<="),

    GreaterThanOrEqual(">="),

    Equality("=="),

    NonEquality("!=");

    private final String name;

    RelationalOperators(String name) {
        this.name = name;
    }

    public static RelationalOperators from(String name) {
        switch (name) {
            case "<" -> {
                return RelationalOperators.SmallerThan;
            }
            case ">" -> {
                return RelationalOperators.GreaterThan;
            }
            case "<=" -> {
                return RelationalOperators.SmallerThanOrEqual;
            }
            case ">=" -> {
                return RelationalOperators.GreaterThanOrEqual;
            }
            case "==" -> {
                return RelationalOperators.Equality;
            }
            case "!=" -> {
                return RelationalOperators.NonEquality;
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

package SemanticAnalyzer.SymbolExpressions;

public enum SymbolExpressionKind {

    returnn("Return"),

    NamePreExpression("NamePreExpression"),

    ArrayPreExpression("ArrayPreExpression"),

    MethodHierarchyPreExpression("MethodHierarchyPreExpression"),

    VariableHierarchyPreExpression("VariableHierarchyPreExpression"),

    BoolExpression("BoolExpression"),

    FloatExpression("FloatExpression"),

    IntegerExpression("IntegerExpression"),

    NoneExpression("NoneExpression"),

    StringExpression("StringExpression"),

    PrefixExpression("PrefixExpression"),

    ClassInstaceExpression("ClassInstaceExpression"),

    ArithmeticExpression("ArithmeticExpression"),

    ParanthesisExpression("ParanthesisExpression"),

    MethodCallExpression("MethodCallExpression"),

    ArrayDeclarationAssignment("ArrayDeclarationAssignment"),

    AssignmentOperatorAssignment("AssignmentOperatorAssignment"),

    VariableDeclarationAssignment("VariableDeclarationAssignment"),

    BoolConditionExpression("BoolConditionExpression"),

    PrefixCondition("PrefixCondition"),

    RelationalOperatorsCondition("RelationalOperatorsCondition"),

    ;

    private final String name;

    SymbolExpressionKind(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

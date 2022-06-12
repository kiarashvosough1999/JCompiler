package SemanticAnalyzer;

public class Helper {

    static public Boolean isPrimitiveType(String type) {
        return switch (type) {
            case "int", "bool", "float", "str", "void" -> true;
            default -> false;
        };
    }
}

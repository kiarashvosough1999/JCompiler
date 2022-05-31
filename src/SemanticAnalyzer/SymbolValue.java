package SemanticAnalyzer;

public interface SymbolValue {

    String getName();
    Object getValue();
    Boolean isDefined();

    String getType();
    String getReturnType();
    String getParameters();
}

package SemanticAnalyzer.SymbolValues;

public interface SymbolValue {

    String getName();
    Object getValue();
    Boolean isDefined();

    SymbolValueKind getKind();

    String getType();
    String getReturnType();
    String getParameters();
}

package SemanticAnalyzer.SymbolValues;

public interface SymbolValue {

    SymbolValueOwner getOwner();

    String getKeyStarter();

    String getName();

    Object getValue();

    Boolean isDefined();

    SymbolValueKind getKind();

    String getType();

    String getReturnType();

    String getParameters();

    Boolean isArray();
}

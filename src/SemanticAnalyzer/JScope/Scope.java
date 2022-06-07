package SemanticAnalyzer.JScope;

import SemanticAnalyzer.SemanticException;
import SemanticAnalyzer.SymbolTable;

import java.util.ArrayList;

public interface Scope {

    SymbolTable getSymbolTable();

    ScopeType getScopeType();

    String getScopeName();

    ArrayList<Scope> getAllChildScopes();

    void insertScope(Scope scope) throws SemanticException;

    Scope getScopeByName(String name) throws SemanticException;
}

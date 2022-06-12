package SemanticAnalyzer.JScope;

import SemanticAnalyzer.SemanticException;
import SemanticAnalyzer.SymbolTable;

import java.util.ArrayList;
import java.util.Stack;

public interface Scope {

    SymbolTable getSymbolTable();

    ScopeType getScopeType();

    String getScopeName();

    Stack<Scope> getAllChildScopes();

    void insertScope(Scope scope) throws SemanticException;

    Scope getScopeByName(String name) throws SemanticException;

    Integer getLineNumber();
}

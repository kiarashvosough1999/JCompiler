package SemanticAnalyzer.JScope;

import SemanticAnalyzer.SemanticException;
import SemanticAnalyzer.SymbolExpressions.SymbolExpression;
import SemanticAnalyzer.SymbolTable;

import java.util.List;
import java.util.Stack;

public interface Scope {

    SymbolTable getSymbolTable();

    ScopeType getScopeType();

    String getScopeName();

    Stack<Scope> getAllChildScopes();

    void insertScope(Scope scope) throws SemanticException;

    void insertSymbolExpression(SymbolExpression symbolExpression) throws SemanticException;

    List<SymbolExpression> getSymbolExpressions();

    Scope getScopeByName(String name) throws SemanticException;

    Integer getLineNumber();
}

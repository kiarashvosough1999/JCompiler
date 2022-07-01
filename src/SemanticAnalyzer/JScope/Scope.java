package SemanticAnalyzer.JScope;

import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.Exceptions.SemanticException;
import SemanticAnalyzer.SymbolTable;
import java.util.Stack;

public interface Scope {

    SymbolTable getSymbolTable();

    ScopeType getScopeType();

    String getScopeName();

    Stack<Scope> getAllChildScopes();

    void insertScope(Scope scope) throws SemanticException;

    Scope getScopeByName(String name) throws SemanticException;

    Integer getLineNumber();

    void insertScopeRedundant(ParameteredScope scope, PositionModel positionModel) throws SemanticException;
}

package SemanticAnalyzer.JScope.Scopes.Block;

import SemanticAnalyzer.JScope.ParameteredScope;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.Exceptions.SemanticException;
import SemanticAnalyzer.SymbolTable;

import java.util.Stack;

public class IfElseScope implements Scope {
    private final SymbolTable symbolTable;

    private final ScopeType scopeType;

    private final String scopeName;

    private final Stack<Scope> childScopes;

    private final Integer lineNumber;

    public IfElseScope(String scopeName, Integer lineNumber) {
        this.lineNumber = lineNumber;
        this.symbolTable = new SymbolTable();
        this.scopeType = ScopeType.block;
        this.scopeName = scopeName;
        this.childScopes = new Stack<>();
    }

    @Override
    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    @Override
    public ScopeType getScopeType() {
        return this.scopeType;
    }

    @Override
    public String getScopeName() {
        return this.scopeName;
    }

    @Override
    public void insertScopeRedundant(ParameteredScope scope, PositionModel positionModel) throws SemanticException {
        String name = scope.getScopeName() + "_" + positionModel.line() + "_" + positionModel.column();
        scope.setScopeName(name);
        childScopes.push(scope);
    }
    @Override
    public Stack<Scope> getAllChildScopes() {
        return this.childScopes;
    }

    @Override
    public void insertScope(Scope scope) throws SemanticException {
        try {
            Scope s = getScopeByName(scope.getScopeName());
        } catch (SemanticException exception) {
            childScopes.push(scope);
            return;
        }
        throw new SemanticException("Can not insert scope " + scope.getScopeName() + " because it is already added");
    }

    @Override
    public Scope getScopeByName(String name) throws SemanticException {
        for (Scope scope : this.childScopes) {
            if (scope.getScopeName().equals(name)) {
                return scope;
            }
        }
        throw new SemanticException("Can not find scope with name" + name);
    }

    @Override
    public Integer getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("----------");
        stringBuilder.append(this.getScopeName());
        stringBuilder.append("  ");
        stringBuilder.append(getLineNumber());
        stringBuilder.append("----------");
        stringBuilder.append("\n");
        stringBuilder.append(getSymbolTable().toString());

        stringBuilder.append("\n");
        for (Scope scope: childScopes) {
            stringBuilder.append(scope.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

package SemanticAnalyzer.Scopes;

import SemanticAnalyzer.Scope;
import SemanticAnalyzer.ScopeType;
import SemanticAnalyzer.SemanticException;
import SemanticAnalyzer.SymbolTable;
import java.util.ArrayList;

public class ProgramScope implements Scope {

    private final SymbolTable symbolTable;

    private final ScopeType scopeType;

    private final String scopeName;

    private final ArrayList<Scope> childScopes;

    public ProgramScope(String scopeName) {
        this.symbolTable = new SymbolTable();
        this.scopeType = ScopeType.program;
        this.scopeName = scopeName;
        this.childScopes = new ArrayList<>();
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
    public ArrayList<Scope> getAllChildScopes() {
        return this.childScopes;
    }

    @Override
    public void insertScope(Scope scope) throws SemanticException {
        try {
            Scope s = getScopeByName(scope.getScopeName());
        } catch (SemanticException exception) {
            childScopes.add(scope);
            return;
        }
        throw new SemanticException("Can not insert scope because it is already added");
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
}

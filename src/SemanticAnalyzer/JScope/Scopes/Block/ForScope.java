package SemanticAnalyzer.JScope.Scopes.Block;

import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.Models.ForSignatureModel;
import SemanticAnalyzer.SemanticException;
import SemanticAnalyzer.SymbolExpressions.SymbolExpression;
import SemanticAnalyzer.SymbolTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ForScope implements Scope {
    private final SymbolTable symbolTable;

    private final ScopeType scopeType;

    private final String scopeName;

    private final Stack<Scope> childScopes;

    private final List<SymbolExpression> symbolExpressionList;

    private final Integer lineNumber;

    private final ForSignatureModel signatureModel;

    public ForScope(String scopeName, Integer lineNumber, ForSignatureModel signatureModel) {
        this.lineNumber = lineNumber;
        this.symbolTable = new SymbolTable();
        this.scopeType = ScopeType.block;
        this.scopeName = scopeName;
        this.signatureModel = signatureModel;
        this.childScopes = new Stack<>();
        this.symbolExpressionList = new ArrayList<>();
    }

    @Override
    public void insertSymbolExpression(SymbolExpression symbolExpression) throws SemanticException {
        symbolExpressionList.add(symbolExpression);
    }

    @Override
    public List<SymbolExpression> getSymbolExpressions() {
        return this.symbolExpressionList;
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

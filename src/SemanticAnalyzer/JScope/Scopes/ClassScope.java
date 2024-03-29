package SemanticAnalyzer.JScope.Scopes;

import SemanticAnalyzer.Models.ClassErrorMeta;
import SemanticAnalyzer.Models.ParentClassModel;
import SemanticAnalyzer.Validators.ErrorProneEntity;
import SemanticAnalyzer.JScope.ParameteredScope;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.Exceptions.SemanticException;
import SemanticAnalyzer.SymbolTable;
import java.util.List;
import java.util.Stack;

public class ClassScope implements Scope, ErrorProneEntity {

    private final SymbolTable symbolTable;

    private final ScopeType scopeType;

    private final String scopeName;

    final private List<ParentClassModel> classParents;

    private final Stack<Scope> childScopes;

    private final Integer lineNumber;

    private final PositionModel namePosition;


    public ClassScope(String scopeName,List<ParentClassModel> classParents,  Integer lineNumber, PositionModel namePosition) {
        this.lineNumber = lineNumber;
        this.namePosition = namePosition;
        this.classParents = classParents;
        this.symbolTable = new SymbolTable();
        this.scopeType = ScopeType.classs;
        this.scopeName = scopeName;
        this.childScopes = new Stack<>();
    }

    public List<ParentClassModel> getClassParents() {
        return classParents;
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
    public void insertScopeRedundant(ParameteredScope scope, PositionModel positionModel) throws SemanticException {
        String name = scope.getScopeName() + "_" + positionModel.line() + "_" + positionModel.column();
        scope.setScopeName(name);
        childScopes.push(scope);
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

        for (Scope scope: childScopes) {
            ParameteredScope parameteredScope = (ParameteredScope) scope;
            if (parameteredScope != null) {
                stringBuilder.append(parameteredScope.getAsValue());
                stringBuilder.append("\n");
            }
        }

        stringBuilder.append("\n");
        for (Scope scope: childScopes) {
            stringBuilder.append(scope.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public Object getErrorProneEntityMeta() {
        return new ClassErrorMeta(
                this.scopeName,
                this.namePosition,
                this.classParents
        );
    }
}

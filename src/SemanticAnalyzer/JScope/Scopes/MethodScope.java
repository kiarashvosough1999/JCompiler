package SemanticAnalyzer.JScope.Scopes;

import Constants.Constants;
import SemanticAnalyzer.Models.MethodReturnModel;
import SemanticAnalyzer.SymbolExpressions.SymbolExpression;
import SemanticAnalyzer.Validators.ErrorProneEntity;
import SemanticAnalyzer.Models.MethodErrorMeta;
import SemanticAnalyzer.Helper;
import SemanticAnalyzer.JScope.ParameteredScope;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.Models.ParameterModel;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SemanticException;
import SemanticAnalyzer.SymbolTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class MethodScope implements ParameteredScope, ErrorProneEntity {

    private final SymbolTable symbolTable;

    private final ScopeType scopeType;

    private final String scopeName;

    private final Stack<Scope> childScopes;

    private final String returnType;

    private final ArrayList<ParameterModel> parameters;

    private final List<SymbolExpression> symbolExpressionList;

    private final Optional<MethodReturnModel> returnModel;

    private final Integer lineNumber;

    private final PositionModel namePosition;

    private final PositionModel returnTypePosition;

    public MethodScope(String scopeName,
                       String returnType,
                       Integer lineNumber,
                       PositionModel namePosition,
                       PositionModel returnTypePosition) {
        this.lineNumber = lineNumber;
        this.namePosition = namePosition;
        this.returnTypePosition = returnTypePosition;
        this.symbolTable = new SymbolTable();
        this.returnType = returnType == null ? "void" : returnType;
        this.scopeType = ScopeType.constructor;
        this.scopeName = scopeName + Constants.Constructor;
        this.returnModel = Optional.empty();
        this.childScopes = new Stack<>();
        this.parameters = new ArrayList<>();
        this.symbolExpressionList = new ArrayList<>();
    }

    public MethodScope(String scopeName,
                       String returnType,
                       Integer lineNumber,
                       PositionModel namePosition,
                       PositionModel returnTypePosition,
                       MethodReturnModel returnModel) {
        this.lineNumber = lineNumber;
        this.namePosition = namePosition;
        this.returnTypePosition = returnTypePosition;
        this.symbolTable = new SymbolTable();
        this.returnType = returnType == null ? "void" : returnType;
        this.scopeType = ScopeType.method;
        this.scopeName = scopeName;
        this.returnModel = Optional.ofNullable(returnModel);
        this.childScopes = new Stack<>();
        this.parameters = new ArrayList<>();
        this.symbolExpressionList = new ArrayList<>();
    }

    public PositionModel getNamePosition() {
        return namePosition;
    }

    public String getReturnType() {
        return returnType;
    }

    @Override
    public List<SymbolExpression> getSymbolExpressionList() {
        return symbolExpressionList;
    }

    @Override
    public List<SymbolExpression> getSymbolExpressions() {
        return this.symbolExpressionList;
    }

    @Override
    public void addParameters(String name, String type, PositionModel namePosition, PositionModel typePosition) {
        parameters.add(new ParameterModel(name, type, namePosition, typePosition));
    }

    @Override
    public ArrayList<ParameterModel> getParameters() {
        return parameters;
    }

    @Override
    public void insertSymbolExpression(SymbolExpression symbolExpression) throws SemanticException {
        symbolExpressionList.add(symbolExpression);
    }

    @Override
    public String getAsValue() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.Key);
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(Constants.Method + Constants.Underline + scopeName);
        stringBuilder.append(Constants.VerticalLine);
        stringBuilder.append(Constants.VALUE);
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(this.scopeType);
        stringBuilder.append(Constants.LeftParan);
        stringBuilder.append(Constants.Name);
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(this.scopeName);
        stringBuilder.append(Constants.RightParan);

        // return type

        stringBuilder.append(Constants.LeftParan);
        stringBuilder.append(Constants.ReturnTYPE);
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(
                Helper.isPrimitiveType(returnType) ? returnType : Constants.ClassTyped + Constants.Equal + returnType
        );
        stringBuilder.append(Constants.RightBracket);

        // params

        if (parameters.size() > 0) {
            stringBuilder.append(Constants.LeftBracket);
            stringBuilder.append(Constants.ParamList);
            stringBuilder.append(Constants.Colon);
            for (int i = 0; i < this.parameters.size(); i++){
                stringBuilder.append(Constants.LeftBracket);
                stringBuilder.append(Constants.TYPE);
                stringBuilder.append(Constants.Colon);
                String returnType = this.parameters.get(i).type();
                stringBuilder.append(
                        Helper.isPrimitiveType(returnType) ? returnType : Constants.ClassTyped + Constants.Equal + returnType
                        );
                stringBuilder.append(Constants.Comma);
                stringBuilder.append(Constants.Index);
                stringBuilder.append(Constants.Colon);
                stringBuilder.append(i);
                stringBuilder.append(Constants.RightBracket);
            }
            stringBuilder.append(Constants.RightBracket);
        }


        stringBuilder.append(Constants.RightParan);
        return stringBuilder.toString();
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

    @Override
    public Object getErrorProneEntityMeta() {
        return new MethodErrorMeta(
                this.scopeName,
                this.returnType,
                this.parameters,
                this.namePosition,
                this.returnTypePosition
        );
    }
}

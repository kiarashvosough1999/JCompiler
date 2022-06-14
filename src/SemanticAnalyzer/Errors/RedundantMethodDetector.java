package SemanticAnalyzer.Errors;

import Constants.Constants;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.JScope.Scopes.MethodScope;

import java.util.Stack;

public class RedundantMethodDetector implements ErrorGenerator {

    private final Stack<Scope> scopes;
    private Scope preDefinedMethodScope = null;

    public RedundantMethodDetector(Stack<Scope> scopes) {
        this.scopes = scopes;
    }

    public Boolean isRedundantMethod(MethodScope methodScope) {
        for (Scope scope: this.scopes) {
            if (scope.getScopeType() == ScopeType.method) {
                if (scope.getScopeName().equals(methodScope.getScopeName())) {
                    preDefinedMethodScope = methodScope;
                    return true;
                }
            }
            for (Scope childScope : scope.getAllChildScopes()) {
                if (childScope.getScopeType() == ScopeType.method) {
                    if (childScope.getScopeName().equals(methodScope.getScopeName())) {
                        preDefinedMethodScope = methodScope;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String generateErrorMessage() {
        if (preDefinedMethodScope != null) {
            ErrorProneNameType errorProneNameType = (ErrorProneNameType)preDefinedMethodScope;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error102 : in line ");
            stringBuilder.append(Constants.LeftBracket);
            stringBuilder.append(errorProneNameType.getNamePosition().line());
            stringBuilder.append(Constants.Colon);
            stringBuilder.append(errorProneNameType.getNamePosition().column());
            stringBuilder.append(Constants.RightBracket);
            stringBuilder.append(" , method ");
            stringBuilder.append(Constants.LeftBracket);
            stringBuilder.append(preDefinedMethodScope.getScopeName());
            stringBuilder.append(Constants.RightBracket);
            stringBuilder.append(" has been defined already");
            return stringBuilder.toString();
        }
        return "  sASA";
    }
}

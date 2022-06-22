package SemanticAnalyzer.Errors;

import Constants.Constants;
import SemanticAnalyzer.Helper;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import java.util.Stack;

//public class UnUsedClassDetector extends Object implements ErrorGenerator {
//
//    private final Stack<Scope> scopes;
//
//    private final ErrorProneNameType errorProneNameType;
//
//    private Scope preDefinedClassScope = null;
//
//    private final String className;
//
//    public UnUsedClassDetector(Stack<Scope> scopes, String className,  ErrorProneNameType errorProneNameType) {
//        this.scopes = scopes;
//        this.errorProneNameType = errorProneNameType;
//        this.className = className;
//    }
//
//    public Boolean isClassUnDefined() {
//        if (Helper.isPrimitiveType(className)) {
//            return false;
//        }
//        for (Scope scope: this.scopes) {
//            if (scope.getScopeType() == ScopeType.classs) {
//                if (scope.getScopeName().equals(className)) {
//                    preDefinedClassScope = scope;
//                    return false;
//                }
//            } else if (scope.getScopeType() == ScopeType.program) {
//                SymbolValue symbolValue = scope.getSymbolTable().getSymbolValueFor(className);
//                if (symbolValue != null) {
//                    preDefinedClassScope = scope;
//                    return false;
//                }
//            }
//            for (Scope childScope : scope.getAllChildScopes()) {
//                if (childScope.getScopeType() == ScopeType.classs) {
//                    if (childScope.getScopeName().equals(className)) {
//                        preDefinedClassScope = childScope;
//                        return false;
//                    }
//                } else if (childScope.getScopeType() == ScopeType.program) {
//                    SymbolValue symbolValue = childScope.getSymbolTable().getSymbolValueFor(className);
//                    if (symbolValue != null) {
//                        preDefinedClassScope = childScope;
//                        return false;
//                    }
//                }
//            }
//            if (scope.getScopeType() == ScopeType.program) break;
//        }
//        return true;
//    }
//
//    @Override
//    public String generateErrorMessage() {
//        if (preDefinedClassScope == null) {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("Error103 : in line ");
//            stringBuilder.append(Constants.LeftBracket);
//            stringBuilder.append(errorProneNameType.getNamePosition().line());
//            stringBuilder.append(Constants.Colon);
//            stringBuilder.append(errorProneNameType.getNamePosition().column());
//            stringBuilder.append(Constants.RightBracket);
//            stringBuilder.append(" , cannot find  class ");
//            stringBuilder.append(Constants.LeftBracket);
//            stringBuilder.append(className);
//            stringBuilder.append(Constants.RightBracket);
//            return stringBuilder.toString();
//        }
//        return "";
//    }
//}

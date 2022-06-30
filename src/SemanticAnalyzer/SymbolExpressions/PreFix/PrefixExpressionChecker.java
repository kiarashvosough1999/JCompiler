package SemanticAnalyzer.SymbolExpressions.PreFix;

import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.SymbolExpressions.PreFix.Name.NamePreExpression;
import SemanticAnalyzer.SymbolExpressions.PreFix.Name.NamePreExpressionMeta;
import SemanticAnalyzer.SymbolExpressions.PreFix.Name.NamePrefixExpressionExtractor;
import java.util.*;

public class PrefixExpressionChecker {

    private final Queue<PreFixSymbolExpression> expressionQueue;

    public PrefixExpressionChecker() {
        this.expressionQueue = new LinkedList<>();
    }

    public void check(PreFixSymbolExpression expression) {
        this.expressionQueue.add(expression);
        expression.getPrefixExpression().ifPresent(s -> {
            this.expressionQueue.add(s);
            this.check(s);
        });
    }

    public void checkQueue(Stack<Scope> currentExpressionScopeStack, List<Scope> allScopes) {
        for (PreFixSymbolExpression expression: expressionQueue) {
            switch (expression.getKind()) {
                case NamePreExpression -> {
                    // check symbol tables of scopes / method params / parents class variables
                    NamePreExpression namePreExpression = (NamePreExpression)expression;
                    Optional<NamePreExpressionMeta> meta = new NamePrefixExpressionExtractor()
                            .checkNameExpression(
                                    namePreExpression,
                                    currentExpressionScopeStack,
                                    allScopes
                            );
                    meta.ifPresent(namePreExpression::setNamePreExpressionMeta);
                }
                default -> {
                }
            }
        }
    }

//    private DFSSearchResultModel<Scope> findStackOfScopes(Scope currentExpressionQueue, List<Scope> allScopes) {
//        Stack<Scope> foundScopes = new Stack<>();
//        for (Scope scope: allScopes) {
//            if (scope.hashCode() == currentExpressionQueue.hashCode()) {
//                foundScopes.push(scope);
//                return new DFSSearchResultModel(true, foundScopes);
//            } else {
//                foundScopes.push(scope);
//                DFSSearchResultModel<Scope> childSearch = this.findStackOfScopes(currentExpressionQueue, scope.getAllChildScopes());
//                if (childSearch.isFounded()) {
//                    foundScopes.addAll(childSearch.graphStack());
//                    return new DFSSearchResultModel(true, foundScopes);
//                } else {
//                    foundScopes.pop();
//                }
//            }
//        }
//        return new DFSSearchResultModel(false, new Stack<>());
//    }
}

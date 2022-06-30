package SemanticAnalyzer.Validators.Usage;

import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.SymbolExpressions.Assignments.AssignmentOperatorAssignment;
import SemanticAnalyzer.SymbolExpressions.Assignments.AssignmentSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.PreFix.PreFixSymbolExpression;
import SemanticAnalyzer.SymbolExpressions.PreFix.PrefixExpressionChecker;
import SemanticAnalyzer.SymbolExpressions.SymbolExpression;
import java.util.List;
import java.util.Stack;

public class DefinedPropertyUsage {

    private final List<Scope> allScopes;

    public DefinedPropertyUsage(List<Scope> allScopes) {
        this.allScopes = allScopes;
    }

    public void run() {
        this.detectUndefinedProperties(allScopes, new Stack<>());
    }

    public void detectUndefinedProperties(List<Scope> scopes, Stack<Scope> previousUsedScope) {
        Stack<Scope> usedScope = new Stack<>();
        for (Scope scope: scopes) {
            usedScope.push(scope);
            Stack<Scope> summedScopes = new Stack<>();
            summedScopes.addAll(previousUsedScope);
            summedScopes.addAll(usedScope);
            for (SymbolExpression symbolExpression: scope.getSymbolExpressions()) {
                try {
                    AssignmentOperatorAssignment assignmentSymbolExpression = (AssignmentOperatorAssignment)symbolExpression;
                    PreFixSymbolExpression preFixSymbolExpression = assignmentSymbolExpression.getLeftPrefixSymbolExpression();
                    if (preFixSymbolExpression != null) {
                        PrefixExpressionChecker ch = new PrefixExpressionChecker();
                        ch.check(preFixSymbolExpression);
                        ch.checkQueue(
                                summedScopes,
                                this.allScopes
                        );
                    }
                } catch (Exception ignored){

                }
            }
            this.detectUndefinedProperties(scope.getAllChildScopes(), summedScopes);
        }
    }
}

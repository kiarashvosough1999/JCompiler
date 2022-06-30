package SemanticAnalyzer.SymbolExpressions.Factories;

import Constants.Constants;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolExpressions.PreFix.*;
import SemanticAnalyzer.SymbolExpressions.PreFix.Array.ArrayPreExpression;
import SemanticAnalyzer.SymbolExpressions.PreFix.MethodHierarchy.MethodHierarchyPreExpression;
import SemanticAnalyzer.SymbolExpressions.PreFix.Name.NamePreExpression;
import SemanticAnalyzer.SymbolExpressions.PreFix.VariableHierarchy.VariableHierarchyPreExpression;
import SemanticAnalyzer.SymbolValues.ArraySymbolValue;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import SemanticAnalyzer.SymbolValues.SymbolValueKind;
import SemanticAnalyzer.Validators.ValidationResultModel;
import gen.JythonParser;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class PreFixExpressionFactory {

    public PreFixExpressionFactory() {
    }

    public ValidationResultModel generatePreFixSymbolExpression(JythonParser.PrefixexpContext prefixexpContext, List<Scope> scopeList) {

        if (prefixexpContext.prefixexp() != null && prefixexpContext.arrayCount != null) {
            if (prefixexpContext.prefixexp().id != null) {
                for (Scope scope : scopeList) {
                    for (Map.Entry<String, SymbolValue> entry : scope.getSymbolTable().getEntrySet()) {
                        if (entry.getKey().equals(prefixexpContext.prefixexp().id.getText()) && entry.getValue().getKind() == SymbolValueKind.arrayField) {
                            try {
                                ArraySymbolValue arraySymbolValue = (ArraySymbolValue) entry.getValue();
                                int excpectedArrayValue = Integer.parseInt(arraySymbolValue.getArraySize());
                                int currentSize = Integer.parseInt(prefixexpContext.arrayCount.getText());
                                if (currentSize < excpectedArrayValue) {
                                    return new ValidationResultModel(
                                            true,
                                            null,
                                            null,
                                            null,
                                            ""
                                    );
                                } else {
                                    return new ValidationResultModel(
                                            false,
                                            null,
                                            null,
                                            null,
                                            this.generateErrorForInvalidArrayIndex(
                                                    new PositionModel(
                                                            prefixexpContext.start.getLine(),
                                                            prefixexpContext.start.getCharPositionInLine()
                                                    ),
                                                    prefixexpContext.prefixexp().id.getText()
                                            )
                                    );
                                }
                            } catch (Exception exception) {
                                return new ValidationResultModel(
                                        true,
                                        null,
                                        null,
                                        null,
                                        "Invalid Index Number"
                                );
                            }
                        }
                    }
                }
            }
            return new ValidationResultModel(
                    false,
                    null,
                    null,
                    null,
                    this.generateErrorForNotFounded(
                            new PositionModel(
                                    prefixexpContext.start.getLine(),
                                    prefixexpContext.start.getCharPositionInLine()
                            ),
                            prefixexpContext.prefixexp().id.getText()
                    )
            );
        } else if (prefixexpContext.nextId != null && prefixexpContext.prefixexp().id != null) {
            for (Scope scope : scopeList) {
                for (Map.Entry<String, SymbolValue> entry : scope.getSymbolTable().getEntrySet()) {
                    if (entry.getKey().equals(prefixexpContext.nextId.getText()) && prefixexpContext.prefixexp().id.getText().equals("self")) {
                        return new ValidationResultModel(
                                true,
                                null,
                                null,
                                null,
                                ""
                        );
                    }
                }
            }
            return new ValidationResultModel(
                    false,
                    null,
                    null,
                    null,
                    this.generateErrorForNotFounded(
                            new PositionModel(
                                    prefixexpContext.start.getLine(),
                                    prefixexpContext.start.getCharPositionInLine()
                            ),
                            "self." + prefixexpContext.nextId.getText()
                    )
            );
        } else if (prefixexpContext.prefixexp() != null && prefixexpContext.methodNAme != null && prefixexpContext.args() != null) {

        } else if (prefixexpContext.ID() != null) {

        }
        return new ValidationResultModel(
                true,
                null,
                null,
                null,
                ""
        );
    }

    private String generateErrorForInvalidArrayIndex(PositionModel methodPosition, String propertyNAme) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error103 : in line ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(methodPosition.line());
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(methodPosition.column());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" , array property  ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(propertyNAme);
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" got an index out of bound");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String generateErrorForNotFounded(PositionModel methodPosition,
                                              String propertyName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error103 : in line ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(methodPosition.line());
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(methodPosition.column());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" , property  ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(propertyName);
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append("was not defined");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}

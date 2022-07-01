package SemanticAnalyzer.Validators.Usage;

import Constants.Constants;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.JScope.Scopes.MethodScope;
import SemanticAnalyzer.Models.ParameterModel;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolValues.ArraySymbolValue;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import SemanticAnalyzer.SymbolValues.SymbolValueKind;
import SemanticAnalyzer.Models.ValidationResultModel;
import gen.JythonParser;
import java.util.*;

public class PreFixExpressionUsageDetector {

    private final Optional<String> shouldReturnType;

    private Optional<String> leftType;

    public PreFixExpressionUsageDetector(Optional<String> shouldReturnType) {
        this.shouldReturnType = shouldReturnType;
        this.leftType = Optional.empty();
    }

    public Optional<String> getLeftType() {
        return leftType;
    }

    public ValidationResultModel generatePreFixSymbolExpression(JythonParser.PrefixexpContext prefixexpContext, List<Scope> scopeList) {

        if (prefixexpContext.id != null) {
            for (Scope scope : scopeList) {
                Set<Map.Entry<String, SymbolValue>> entrySet = new HashSet<>();
                entrySet.addAll(scope.getSymbolTable().getEntrySet());
                if (scope.getScopeType() == ScopeType.method || scope.getScopeType() == ScopeType.constructor) {
                    List<ParameterModel> parameterModelList = ((MethodScope)scope).getParameters();
                    for (ParameterModel param : parameterModelList) {
                        entrySet.add(
                                new AbstractMap.SimpleEntry<>(param.name(), param.getSymbolValue())
                        );
                    }
                }
                for (Map.Entry<String, SymbolValue> entry : entrySet) {
                    if (entry.getKey().equals(prefixexpContext.id.getText())) {
                        if (this.shouldReturnType.isPresent() && entry.getValue().getType().equals(this.shouldReturnType.get())) {
                            this.leftType = Optional.of(entry.getValue().getType());
                            return new ValidationResultModel(
                                    true,
                                    null,
                                    null,
                                    null,
                                    ""
                            );
                        } else if (this.shouldReturnType.isPresent()){
                            this.leftType = Optional.of(entry.getValue().getType());
                            return new ValidationResultModel(
                                    false,
                                    null,
                                    null,
                                    null,
                                    this.generateErrorMessageForExpression(
                                            new PositionModel(
                                                    prefixexpContext.id.getLine(),
                                                    prefixexpContext.id.getCharPositionInLine()
                                            ),
                                            entry.getValue().getType(),
                                            entry.getKey()
                                    )
                            );
                        } else {
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
            }
            return new ValidationResultModel(
                    false,
                    null,
                    null,
                    null,
                    this.generateErrorForNotFounded(
                            new PositionModel(
                                    prefixexpContext.id.getLine(),
                                    prefixexpContext.id.getCharPositionInLine()
                            ),
                            prefixexpContext.id.getText()
                    )
            );
        } else if (prefixexpContext.prefixexp() != null && prefixexpContext.arrayCount != null) {
            if (prefixexpContext.prefixexp().id != null) {
                for (Scope scope : scopeList) {
                    Set<Map.Entry<String, SymbolValue>> entrySet = scope.getSymbolTable().getEntrySet();
                    if (scope.getScopeType() == ScopeType.method || scope.getScopeType() == ScopeType.constructor) {
                        List<ParameterModel> parameterModelList = ((MethodScope)scope).getParameters();
                        for (ParameterModel param : parameterModelList) {
                            entrySet.add(
                                    new AbstractMap.SimpleEntry<>(param.name(), param.getSymbolValue())
                            );
                        }
                    }
                    for (Map.Entry<String, SymbolValue> entry : entrySet) {
                        if (entry.getKey().equals(prefixexpContext.prefixexp().id.getText()) && entry.getValue().getKind() == SymbolValueKind.arrayField) {
                            try {
                                ArraySymbolValue arraySymbolValue = (ArraySymbolValue) entry.getValue();
                                int excpectedArrayValue = Integer.parseInt(arraySymbolValue.getArraySize());
                                int currentSize = Integer.parseInt(prefixexpContext.arrayCount.getText());
                                if (currentSize < excpectedArrayValue) {
                                    this.leftType = Optional.of(arraySymbolValue.getType());
                                    return new ValidationResultModel(
                                            true,
                                            null,
                                            null,
                                            null,
                                            ""
                                    );
                                } else if (this.shouldReturnType.map( s -> s.equals(arraySymbolValue.getType())).orElse(false)) {
                                    return new ValidationResultModel(
                                            false,
                                            null,
                                            null,
                                            null,
                                            this.generateErrorMessageForExpression(
                                                    new PositionModel(
                                                            prefixexpContext.start.getLine(),
                                                            prefixexpContext.start.getCharPositionInLine()
                                                    ),
                                                    prefixexpContext.prefixexp().id.getText(),
                                                    entry.getKey()
                                            )
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
                                                    arraySymbolValue.getType()
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
                Set<Map.Entry<String, SymbolValue>> entrySet = scope.getSymbolTable().getEntrySet();
                if (scope.getScopeType() == ScopeType.method || scope.getScopeType() == ScopeType.constructor) {
                    List<ParameterModel> parameterModelList = ((MethodScope)scope).getParameters();
                    for (ParameterModel param : parameterModelList) {
                        entrySet.add(
                                new AbstractMap.SimpleEntry<>(param.name(), param.getSymbolValue())
                        );
                    }
                }
                for (Map.Entry<String, SymbolValue> entry : entrySet) {
                    if (entry.getKey().equals(prefixexpContext.nextId.getText()) && prefixexpContext.prefixexp().id.getText().equals("self")) {
                        if (this.shouldReturnType.isPresent()) {
                            if (this.shouldReturnType.get().equals(entry.getValue().getType())) {
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
                                        this.generateErrorMessageForExpression(
                                                new PositionModel(
                                                        prefixexpContext.nextId.getLine(),
                                                        prefixexpContext.nextId.getCharPositionInLine()
                                                ),
                                                entry.getValue().getType(),
                                                entry.getKey()
                                        )
                                );
                            }
                        } else {
                            this.leftType = Optional.of(entry.getValue().getType());
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

    private String generateErrorMessageForExpression(PositionModel positionModel, String value, String name) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error110 : in line ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(positionModel.line());
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(positionModel.column());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" , expression  ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(name);
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" with value ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(value);
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append("does not match with (right/left) expression  type");
        if (this.shouldReturnType.isPresent()) {
            stringBuilder.append(": ");
            stringBuilder.append(this.shouldReturnType.get());
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String generateErrorForInvalidArrayIndex(PositionModel methodPosition, String propertyNAme) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error111 : in line ");
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

    private String generateErrorForNotFounded(PositionModel methodPosition, String propertyName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error112 : in line ");
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

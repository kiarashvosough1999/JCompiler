package SemanticAnalyzer.Validators.Usage;

import Constants.Constants;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.Models.ValidationResultModel;
import gen.JythonParser;
import java.util.List;
import java.util.Optional;

public final class ExpressionUsageDetector {

    private final Optional<String> shouldReturnType;

    public ExpressionUsageDetector(Optional<String> shouldReturnType) {
        this.shouldReturnType = shouldReturnType;
    }

    public ValidationResultModel generateFixSymbolExpression (JythonParser.ExpContext context, List<Scope> scopeList) {
        if (context.BOOLVALUES() != null) {
            if (shouldReturnType.isPresent()) {
                if (shouldReturnType.get().equals("bool")) {
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
                            this.generateErrorMessage(
                                    new PositionModel(
                                            context.start.getLine(),
                                            context.start.getCharPositionInLine()
                                    ),
                                    context.BOOLVALUES().getSymbol().getText()
                            )
                    );
                }
            }
        } else if (context.INTEGER() != null) {
            if (shouldReturnType.isPresent()) {
                if (shouldReturnType.get().equals("bool")) {
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
                            this.generateErrorMessage(
                                    new PositionModel(
                                            context.start.getLine(),
                                            context.start.getCharPositionInLine()
                                    ),
                                    context.INTEGER().getSymbol().getText()
                            )
                    );
                }
            }
        } else if (context.STRINGVALUE() != null) {
            if (shouldReturnType.isPresent()) {
                if (shouldReturnType.get().equals("bool")) {
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
                            this.generateErrorMessage(
                                    new PositionModel(
                                            context.start.getLine(),
                                            context.start.getCharPositionInLine()
                                    ),
                                    context.STRINGVALUE().getSymbol().getText()
                            )
                    );
                }
            }
        } else if (context.FLOAT() != null) {
            if (shouldReturnType.isPresent()) {
                if (shouldReturnType.get().equals("bool")) {
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
                            this.generateErrorMessage(
                                    new PositionModel(
                                            context.start.getLine(),
                                            context.start.getCharPositionInLine()
                                    ),
                                    context.FLOAT().getSymbol().getText()
                            )
                    );
                }
            }
        }
        else if (context.prefixexp() != null) {
            return new PreFixExpressionUsageDetector(this.shouldReturnType)
                    .generatePreFixSymbolExpression(
                            context.prefixexp(),
                            scopeList
                    );
        }
//        else if (context.exp().size() >= 1 && context.arithmetic_operator() != null) {
//            List<FixSymbolExpression> list = context
//                    .exp()
//                    .parallelStream()
//                    .map(childContext -> new ExpressionFactory(shouldReturnType).generateFixSymbolExpression(childContext))
//                    .toList();
//            if (list.size() == 1) {
//                return new ArithmeticExpression(
//                        list.get(0),
//                        ArithmeticOperator.from(context.arithmetic_operator().getText()),
//                        null
//                );
//            } else if (list.size() == 2) {
//                return new ArithmeticExpression(
//                        list.get(0),
//                        ArithmeticOperator.from(context.arithmetic_operator().getText()),
//                        list.get(1)
//                );
//            } else {
//                // invalid state
//                return new ArithmeticExpression(
//                        null,
//                        ArithmeticOperator.from(context.arithmetic_operator().getText()),
//                        null
//                );
//            }
//        }
        else if (context.class_instance() != null) {
            return new ClassInstanceUsageDetector(shouldReturnType)
                    .validateClassInstance(
                            context.class_instance(),
                            scopeList
                    );
        }
        else if (context.exp().size() == 1) {
            return new ExpressionUsageDetector(this.shouldReturnType)
                    .generateFixSymbolExpression(
                            context.exp().get(0),
                            scopeList
                    );
        }
        else if (context.method_call() != null) {
            return new MethodCallUsageDetector(this.shouldReturnType)
                    .generateMethodCall(
                            context.method_call(),
                            scopeList
                    );
        }
//        else {
//            return new NoneExpression();
//        }

        return new ValidationResultModel(
                true,
                null,
                null,
                null,
                ""
        );
    }

    private String generateErrorMessage(PositionModel positionModel, String value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error107 : in line ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(positionModel.line());
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(positionModel.column());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" , expression  with value");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(value);
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append("does not match with (right/left) expression type");
        if (this.shouldReturnType.isPresent()) {
            stringBuilder.append(":");
            stringBuilder.append(this.shouldReturnType.get());
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}

package SemanticAnalyzer.Validators.Usage;

import Constants.Constants;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.Models.ParameterModel;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.Models.ValidationResultModel;
import gen.JythonParser;
import java.util.List;
import java.util.Optional;

public class ArgsUsageDetector {

    private final Optional<String> shouldReturnType;

    public ArgsUsageDetector(Optional<String> shouldReturnType) {
        this.shouldReturnType = shouldReturnType;
    }

    public ValidationResultModel generateArgsList(JythonParser.ArgsContext context,
                                                  List<Scope> scopeList,
                                                  List<ParameterModel> parameterList,
                                                  PositionModel methodPosition,
                                                  String methodName) {
        if (context.explist() == null && parameterList.size() > 0) {
            return new ValidationResultModel(
                    false,
                    null,
                    null,
                    null,
                    this.generateErrorForBool(
                            context.start.getLine(),
                            context.start.getCharPositionInLine(),
                            "_",
                            methodName,
                            parameterList.size() + "-parameters"
                    )
            );
        } else if (context.explist() != null && parameterList.size() == 0) {
            return new ValidationResultModel(
                    false,
                    null,
                    null,
                    null,
                    this.generateErrorForBool(
                            context.start.getLine(),
                            context.start.getCharPositionInLine(),
                            "_",
                            methodName,
                            "0-parameter"
                    )
            );
        } else if (context.explist() == null && parameterList.size() == 0) {
            return new ValidationResultModel(
                    true,
                    null,
                    null,
                    null,
                    ""
            );
        }

        int i = 0;
        for (JythonParser.ExpContext expContext : context.explist().exp()) {

            if (i > (parameterList.size() - 1)) {
                return new ValidationResultModel(
                        false,
                        null,
                        null,
                        null,
                        this.generateErrorForTooManyARG(
                                methodPosition,
                                methodName
                        )
                );
            }

            ParameterModel currentParameter = parameterList.get(i);

            if (expContext.BOOLVALUES() != null) {
                if (currentParameter.type().equals("bool") && this.checkShouldReturnType("bool")){
                    return new ValidationResultModel(
                            true,
                            null,
                            null,
                            null,
                            ""
                    );
                }else if (currentParameter.type().equals("bool")) {
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
                            this.generateErrorForBool(
                                    expContext.BOOLVALUES().getSymbol().getLine(),
                                    expContext.BOOLVALUES().getSymbol().getCharPositionInLine(),
                                    expContext.BOOLVALUES().getSymbol().getText(),
                                    methodName,
                                    currentParameter.type()
                            )
                    );
                }
            } else if (expContext.INTEGER() != null) {
                if (currentParameter.type().equals("int") && this.checkShouldReturnType("int")){
                    return new ValidationResultModel(
                            true,
                            null,
                            null,
                            null,
                            ""
                    );
                } else if (currentParameter.type().equals("int")) {
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
                            this.generateErrorForBool(
                                    expContext.INTEGER().getSymbol().getLine(),
                                    expContext.INTEGER().getSymbol().getCharPositionInLine(),
                                    expContext.INTEGER().getSymbol().getText(),
                                    methodName,
                                    currentParameter.type()
                            )
                    );
                }
            } else if (expContext.STRINGVALUE() != null) {
                if (currentParameter.type().equals("string") && this.checkShouldReturnType("string")){
                    return new ValidationResultModel(
                            true,
                            null,
                            null,
                            null,
                            ""
                    );
                } else if (currentParameter.type().equals("string")) {
                    return new ValidationResultModel(
                            true,
                            null,
                            null,
                            null,
                            ""
                    );
                }else {
                    return new ValidationResultModel(
                            false,
                            null,
                            null,
                            null,
                            this.generateErrorForBool(
                                    expContext.STRINGVALUE().getSymbol().getLine(),
                                    expContext.STRINGVALUE().getSymbol().getCharPositionInLine(),
                                    expContext.STRINGVALUE().getSymbol().getText(),
                                    methodName,
                                    currentParameter.type()
                            )
                    );
                }
            } else if (expContext.FLOAT() != null) {
                if (currentParameter.type().equals("float") && this.checkShouldReturnType("float")){
                    return new ValidationResultModel(
                            true,
                            null,
                            null,
                            null,
                            ""
                    );
                } else if (currentParameter.type().equals("float")) {
                    return new ValidationResultModel(
                            true,
                            null,
                            null,
                            null,
                            ""
                    );
                }else {
                    return new ValidationResultModel(
                            false,
                            null,
                            null,
                            null,
                            this.generateErrorForBool(
                                    expContext.FLOAT().getSymbol().getLine(),
                                    expContext.FLOAT().getSymbol().getCharPositionInLine(),
                                    expContext.FLOAT().getSymbol().getText(),
                                    methodName,
                                    currentParameter.type()
                            )
                    );
                }
            } else if (expContext.prefixexp() != null) {
                //
            } else if (expContext.exp().size() >= 1 && expContext.arithmetic_operator() != null) {
                //
            } else if (expContext.class_instance() != null) {
                return new ClassInstanceUsageDetector(Optional.of(currentParameter.type())).validateClassInstance(expContext.class_instance(), scopeList);
            } else if (expContext.method_call() != null) {
                return new MethodCallUsageDetector(Optional.of(currentParameter.type())).generateMethodCall(expContext.method_call(), scopeList);
            } else if (expContext.exp().size() == 1) {
                //
            }
            i++;
        } // end for
        return new ValidationResultModel(
                false,
                null,
                null,
                null,
                ""
        );
    }// end method

    private Boolean checkShouldReturnType(String type){
        return shouldReturnType.map(s -> s.equals(type)).orElse(true);
    }

    private String generateErrorForBool(Integer line, Integer column,String symbolText, String methodName, String expectedType) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error104 : in line ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(line);
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(column);
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" , parameter  ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(symbolText);
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" passed to method ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(methodName);
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append("does not match method parameters signature which excpects: ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(expectedType);
        stringBuilder.append(Constants.RightBracket);
        return stringBuilder.toString();
    }

    private String generateErrorForTooManyARG(PositionModel methodPosition,
                                              String methodName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error105 : in line ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(methodPosition.line());
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(methodPosition.column());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" , method  ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(methodName);
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append("was provided with too many parameters");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}

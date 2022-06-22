package SemanticAnalyzer;

import Constants.Constants;
import SemanticAnalyzer.Errors.RedundantMethodStrategy;
import SemanticAnalyzer.Errors.RedundantPropertyStrategy;
import SemanticAnalyzer.Errors.ValidationResultModel;
import SemanticAnalyzer.JScope.ParameteredScope;
import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.JScope.ScopeType;
import SemanticAnalyzer.JScope.Scopes.BlockScope;
import SemanticAnalyzer.JScope.Scopes.ClassScope;
import SemanticAnalyzer.JScope.Scopes.MethodScope;
import SemanticAnalyzer.JScope.Scopes.ProgramScope;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolValues.SymbolValueOwner;
import SemanticAnalyzer.SymbolValues.Values.*;
import gen.JythonListener;
import gen.JythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.ArrayList;
import java.util.Stack;

public class JythonSemanticAnalyzer implements JythonListener {

    private final ArrayList<Scope> topScopes = new ArrayList<>();

    private final Stack<Scope> scopes = new Stack<>();

    private final Stack<ValidationResultModel> errorStack = new Stack<>();

    private Boolean isOnParameters = false;

    /**
     * Enter a parse tree produced by {@link JythonParser#program}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterProgram(JythonParser.ProgramContext ctx) {
        ProgramScope programScope = new ProgramScope("Program 1", ctx.start.getLine());
        scopes.push(programScope);
        topScopes.add(programScope);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#program}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitProgram(JythonParser.ProgramContext ctx) {
        for (Scope scope : scopes) {
            System.out.println(scope);
        }
        for (ValidationResultModel resultModel : errorStack) {
            System.out.println(resultModel);
        }
        scopes.pop();
        System.out.println("lines" + Integer.valueOf(ctx.start.getLine()).toString());
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#importclass}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterImportclass(JythonParser.ImportclassContext ctx) {
        System.out.println();
        ImportValue importValue = new ImportValue(
                ctx.importName.getText(),
                new PositionModel(
                        ctx.importName.getLine(),
                        ctx.importName.getCharPositionInLine()
                ),
                SymbolValueOwner.from(scopes.peek().getScopeType())
        );
        scopes.peek().getSymbolTable().insert(importValue);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#importclass}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitImportclass(JythonParser.ImportclassContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#classDef}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterClassDef(JythonParser.ClassDefContext ctx) {
        ArrayList<String> stringArrayList = new ArrayList<>();

        if (ctx.classParentName != null) {
            stringArrayList.add(ctx.classParentName.getText());
            if (ctx.otherClassParentName != null) {
                stringArrayList.add(ctx.otherClassParentName.getText());
            }
        }

        ClasssValue classValue = new ClasssValue(
                ctx.className.getText(),
                stringArrayList,
                new PositionModel(
                        ctx.className.getLine(),
                        ctx.className.getCharPositionInLine()
                ),
                null,
                SymbolValueOwner.from(scopes.peek().getScopeType()));
        scopes.peek().getSymbolTable().insert(classValue);

        // New Scope

        ClassScope classScope = new ClassScope(
                ctx.className.getText(),
                ctx.start.getLine(),
                new PositionModel(
                        ctx.start.getLine(),
                        ctx.className.getCharPositionInLine()
                )
        );

        try {
            scopes.peek().insertScope(classScope);
        } catch (SemanticException semanticException) {
            System.out.println(semanticException);
        }
        scopes.push(classScope);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#classDef}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitClassDef(JythonParser.ClassDefContext ctx) {
        scopes.pop();
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#class_body}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterClass_body(JythonParser.Class_bodyContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#class_body}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitClass_body(JythonParser.Class_bodyContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#varDec}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterVarDec(JythonParser.VarDecContext ctx) {
        if (isOnParameters) { return; }

        FieldValue fieldValue = new FieldValue(
                ctx.varibaleName.getText(),
                ctx.variableType.getText(),
                new PositionModel(
                        ctx.varibaleName.getLine(),
                        ctx.varibaleName.getCharPositionInLine()
                ),
                new PositionModel(
                        ctx.variableType.getLine(),
                        ctx.variableType.getCharPositionInLine()
                ),
                SymbolValueOwner.from(scopes.peek().getScopeType())
        );

        RedundantPropertyStrategy redundantPropertyStrategy = new RedundantPropertyStrategy(
                this.scopes
        );

        try {
            ValidationResultModel validationResultModel = redundantPropertyStrategy.checkValidity(fieldValue);
            if (validationResultModel.isValid()) {
                scopes.peek().getSymbolTable().insert(fieldValue);
            } else {
                errorStack.push(validationResultModel);
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#varDec}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitVarDec(JythonParser.VarDecContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#arrayDec}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterArrayDec(JythonParser.ArrayDecContext ctx) {
        if (isOnParameters) { return; }
        ArrayFieldValue fieldValue = new ArrayFieldValue(
                ctx.arrayVaribaleName.getText(),
                ctx.arrayType.getText(),
                ctx.arraySize.getText(),
                new PositionModel(
                        ctx.arrayVaribaleName.getLine(),
                        ctx.arrayVaribaleName.getCharPositionInLine()
                ),
                new PositionModel(
                        ctx.arrayType.getLine(),
                        ctx.arrayType.getCharPositionInLine()
                ),
                SymbolValueOwner.from(scopes.peek().getScopeType())
        );
        scopes.peek().getSymbolTable().insert(fieldValue);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#arrayDec}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitArrayDec(JythonParser.ArrayDecContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#methodDec}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterMethodDec(JythonParser.MethodDecContext ctx) {
        MethodScope methodScope = new MethodScope(
                ctx.methodName.getText(),
                ctx.methodReturnType.getText(),
                false,
                ctx.start.getLine(),
                new PositionModel(
                        ctx.start.getLine(),
                        ctx.methodName.getCharPositionInLine()
                ),
                new PositionModel(
                        ctx.start.getLine(),
                        ctx.methodReturnType.getCharPositionInLine()
                )
        );

        RedundantMethodStrategy strategy = new RedundantMethodStrategy(
                this.scopes,
                methodScope
        );

        try {
            ValidationResultModel res = strategy.checkValidity(methodScope);

            if (res.isValid()) {
                scopes.peek().insertScope(methodScope);
                scopes.push(methodScope);
            } else {
                errorStack.push(res);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#methodDec}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitMethodDec(JythonParser.MethodDecContext ctx) {
        if (scopes.peek().getScopeType() == ScopeType.method) {
            scopes.pop();
        }
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#constructor}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterConstructor(JythonParser.ConstructorContext ctx) {

        MethodScope methodScope = new MethodScope(
                ctx.cosntructorType.getText(),
                ctx.cosntructorType.getText(),
                true,
                ctx.start.getLine(),
                new PositionModel(
                        ctx.start.getLine(),
                        ctx.CLASSNAME().getSymbol().getCharPositionInLine()
                ),
                new PositionModel(
                        ctx.start.getLine(),
                        ctx.CLASSNAME().getSymbol().getCharPositionInLine()
                )
        );
        try {
            scopes.peek().insertScope(methodScope);
        } catch (SemanticException semanticException) {
            System.out.println(semanticException);
        }
        scopes.push(methodScope);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#constructor}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitConstructor(JythonParser.ConstructorContext ctx) {
        if (scopes.peek().getScopeType() == ScopeType.constructor) {
            scopes.pop();
        }
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#parameter}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterParameter(JythonParser.ParameterContext ctx) {
        isOnParameters = true;
        ParameteredScope peek = (ParameteredScope)scopes.peek();
        if (peek != null){
            for (JythonParser.VarDecContext context :ctx.varDec()) {
                peek.addParameters(
                        context.varibaleName.getText(),
                        context.variableType.getText(),
                        new PositionModel(
                                context.start.getLine(),
                                context.varibaleName.getCharPositionInLine()
                        ),
                        new PositionModel(
                                context.start.getLine(),
                                context.variableType.getCharPositionInLine()
                        )
                );
            }
        }
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#parameter}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitParameter(JythonParser.ParameterContext ctx) {
        isOnParameters = false;
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#statement}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterStatement(JythonParser.StatementContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#statement}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitStatement(JythonParser.StatementContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#return_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterReturn_statment(JythonParser.Return_statmentContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#return_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitReturn_statment(JythonParser.Return_statmentContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#condition_list}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterCondition_list(JythonParser.Condition_listContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#condition_list}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitCondition_list(JythonParser.Condition_listContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#condition}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterCondition(JythonParser.ConditionContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#condition}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitCondition(JythonParser.ConditionContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#if_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterIf_statment(JythonParser.If_statmentContext ctx) {
        BlockScope blockScope = new BlockScope(Constants.If, ctx.start.getLine());
        try {
            scopes.peek().insertScope(blockScope);
        } catch (SemanticException semanticException) {
            System.out.println(semanticException);
        }
        scopes.push(blockScope);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#if_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitIf_statment(JythonParser.If_statmentContext ctx) {
        scopes.pop();
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#while_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterWhile_statment(JythonParser.While_statmentContext ctx) {
        BlockScope blockScope = new BlockScope(Constants.While, ctx.start.getLine());
        try {
            scopes.peek().insertScope(blockScope);
        } catch (SemanticException semanticException) {
            System.out.println(semanticException);
        }
        scopes.push(blockScope);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#while_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitWhile_statment(JythonParser.While_statmentContext ctx) {
        scopes.pop();
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#if_else_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterIf_else_statment(JythonParser.If_else_statmentContext ctx) {
        BlockScope blockScope = new BlockScope(Constants.ElseIf, ctx.start.getLine());
        try {
            scopes.peek().insertScope(blockScope);
        } catch (SemanticException semanticException) {
            System.out.println(semanticException);
        }
        scopes.push(blockScope);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#if_else_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitIf_else_statment(JythonParser.If_else_statmentContext ctx) {
        scopes.pop();
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#print_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterPrint_statment(JythonParser.Print_statmentContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#print_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitPrint_statment(JythonParser.Print_statmentContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#for_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterFor_statment(JythonParser.For_statmentContext ctx) {
        BlockScope blockScope = new BlockScope(Constants.For, ctx.start.getLine());
        try {
            scopes.peek().insertScope(blockScope);
        } catch (SemanticException semanticException) {
            System.out.println(semanticException);
        }
        scopes.push(blockScope);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#for_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitFor_statment(JythonParser.For_statmentContext ctx) {
        scopes.pop();
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#method_call}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterMethod_call(JythonParser.Method_callContext ctx) {
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#method_call}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitMethod_call(JythonParser.Method_callContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#assignment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterAssignment(JythonParser.AssignmentContext ctx) {
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#assignment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitAssignment(JythonParser.AssignmentContext ctx) {

    }

    @Override
    public void enterExp(JythonParser.ExpContext ctx) {

    }

    @Override
    public void exitExp(JythonParser.ExpContext ctx) {

    }

    @Override
    public void enterPrefixexp(JythonParser.PrefixexpContext ctx) {

    }

    @Override
    public void exitPrefixexp(JythonParser.PrefixexpContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#args}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterArgs(JythonParser.ArgsContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#args}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitArgs(JythonParser.ArgsContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#explist}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterExplist(JythonParser.ExplistContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#explist}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitExplist(JythonParser.ExplistContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#arithmetic_operator}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterArithmetic_operator(JythonParser.Arithmetic_operatorContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#arithmetic_operator}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitArithmetic_operator(JythonParser.Arithmetic_operatorContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#relational_operators}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterRelational_operators(JythonParser.Relational_operatorsContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#relational_operators}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitRelational_operators(JythonParser.Relational_operatorsContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#assignment_operators}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterAssignment_operators(JythonParser.Assignment_operatorsContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#assignment_operators}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitAssignment_operators(JythonParser.Assignment_operatorsContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {
    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {
    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}

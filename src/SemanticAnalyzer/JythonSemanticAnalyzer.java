package SemanticAnalyzer;

import SemanticAnalyzer.Scopes.ProgramScope;
import SemanticAnalyzer.SymbolValues.ImportValue;
import gen.JythonListener;
import gen.JythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

public class JythonSemanticAnalyzer implements JythonListener {

    private final ArrayList<Scope> scopes = new ArrayList<>();
    private Scope ongoingScope = null;

    /**
     * Enter a parse tree produced by {@link JythonParser#program}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterProgram(JythonParser.ProgramContext ctx) {
//        System.out.println("here");
        ProgramScope programScope = new ProgramScope("Program 1");
        scopes.add(programScope);
        ongoingScope = programScope;
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#program}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitProgram(JythonParser.ProgramContext ctx) {
        ongoingScope = null;
//        System.out.println(scopes.size());
        for (Scope scope : scopes) {
            System.out.println(scope.getSymbolTable().toString());
        }
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#importclass}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterImportclass(JythonParser.ImportclassContext ctx) {
//        System.out.println("here2");
        ImportValue importValue = new ImportValue(ctx.importName.getText());
        ongoingScope.getSymbolTable().insert(importValue);
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

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#classDef}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitClassDef(JythonParser.ClassDefContext ctx) {

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

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#methodDec}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitMethodDec(JythonParser.MethodDecContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#constructor}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterConstructor(JythonParser.ConstructorContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#constructor}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitConstructor(JythonParser.ConstructorContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#parameter}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterParameter(JythonParser.ParameterContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#parameter}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitParameter(JythonParser.ParameterContext ctx) {

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

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#if_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitIf_statment(JythonParser.If_statmentContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#while_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterWhile_statment(JythonParser.While_statmentContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#while_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitWhile_statment(JythonParser.While_statmentContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser#if_else_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterIf_else_statment(JythonParser.If_else_statmentContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#if_else_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitIf_else_statment(JythonParser.If_else_statmentContext ctx) {

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

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#for_statment}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitFor_statment(JythonParser.For_statmentContext ctx) {

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

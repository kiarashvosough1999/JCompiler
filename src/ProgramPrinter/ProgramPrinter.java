// Copyright (c) 2022 Kiarash Vosough

//  Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files (the "Software"), to deal
//  in the Software without restriction, including without limitation the rights
//  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//  copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions:

//  The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package ProgramPrinter;

import ProgramPrinter.Indentation.IndentationStack;
import ProgramPrinter.Indentation.IndentationStackException;
import ProgramPrinter.Indentation.IndentationType;
import gen.JythonListener;
import gen.JythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ProgramPrinter implements JythonListener {

    private final IndentationStack indentationStack = new IndentationStack();

    /**
     * Enter a parse tree produced by {@link JythonParser#program}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterProgram(JythonParser.ProgramContext ctx) {
        String s1 = "program start {";
        System.out.println(s1);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#program}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitProgram(JythonParser.ProgramContext ctx) {
        String s1 = "}";
        System.out.println(s1);
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#importclass}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterImportclass(JythonParser.ImportclassContext ctx) {
        try {
            this.indentationStack.push(IndentationType.importt);
        } catch (IndentationStackException ignored) {}

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.indentationStack.getIndentation());
        stringBuilder.append("import class: ");
        stringBuilder.append(ctx.importName.getText());
        System.out.println(stringBuilder);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#importclass}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitImportclass(JythonParser.ImportclassContext ctx) {
        try {
            this.indentationStack.pop(IndentationType.importt);
        } catch (IndentationStackException ignored) { }
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#classDef}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterClassDef(JythonParser.ClassDefContext ctx) {
        try {
            this.indentationStack.push(IndentationType.classs);
        } catch (IndentationStackException ignored) {}

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.indentationStack.getIndentation());
        stringBuilder.append("class: ");
        stringBuilder.append(ctx.className.getText());

        if (ctx.classParentName != null) {
            stringBuilder.append("/ ");
            stringBuilder.append("class parents: ");
            stringBuilder.append(ctx.classParentName.getText());
            stringBuilder.append(", ");
            if (ctx.otherClassParentName != null) {
                stringBuilder.append(ctx.otherClassParentName.getText());
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("{");
        System.out.println(stringBuilder);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#classDef}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitClassDef(JythonParser.ClassDefContext ctx) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.indentationStack.getIndentation());
        stringBuilder.append("}");
        System.out.println(stringBuilder);
        try {
            this.indentationStack.pop(IndentationType.classs);
        } catch (IndentationStackException ignored) {}
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
        // parameters
        if (ctx.parent.parent.getChild(0).getText().equals("def")) {
        } else { // local declarations
            try {
                this.indentationStack.push(IndentationType.variable);
            } catch (IndentationStackException ignored) {}

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.indentationStack.getIndentation());
            stringBuilder.append("field: ");
            stringBuilder.append(ctx.varibaleName.getText());
            stringBuilder.append("/ type= ");
            stringBuilder.append(ctx.variableType.getText());
            System.out.println(stringBuilder);
        }
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#varDec}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitVarDec(JythonParser.VarDecContext ctx) {
        if (ctx.parent.parent.getChild(0).getText().equals("def")) {
        } else {
            try {
                this.indentationStack.pop(IndentationType.variable);
            } catch (IndentationStackException ignored) {}
        }
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#arrayDec}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterArrayDec(JythonParser.ArrayDecContext ctx) {
        try {
            this.indentationStack.push(IndentationType.array);
        } catch (IndentationStackException ignored) {}

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.indentationStack.getIndentation());
        stringBuilder.append("field: ");
        stringBuilder.append(ctx.arrayVaribaleName.getText());
        stringBuilder.append("/ type= ");
        stringBuilder.append(ctx.arrayType.getText());
        System.out.println(stringBuilder);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#arrayDec}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitArrayDec(JythonParser.ArrayDecContext ctx) {
        try {
            this.indentationStack.pop(IndentationType.array);
        } catch (IndentationStackException ignored) {}
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#methodDec}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterMethodDec(JythonParser.MethodDecContext ctx) {
        try {
            this.indentationStack.push(IndentationType.method);
        } catch (IndentationStackException ignored) {}

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.indentationStack.getIndentation());

        if (ctx.methodName.getText().equals("main")) {
            stringBuilder.append(ctx.methodName.getText());
            stringBuilder.append(" method");
        } else {
            stringBuilder.append("class method: ");
            stringBuilder.append(ctx.methodName.getText());
            if (ctx.methodReturnType != null && !ctx.methodReturnType.getText().equals("void")) {
                stringBuilder.append("/ return type: ");
                stringBuilder.append(ctx.methodReturnType.getText());
            }
        }

        stringBuilder.append(" {");
        System.out.println(stringBuilder);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#methodDec}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitMethodDec(JythonParser.MethodDecContext ctx) {

        if (this.indentationStack.getNestedCounter() > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.indentationStack.getIndentation());
            for (int i = 0; i< this.indentationStack.getNestedCounter(); i++){
                stringBuilder.append("    ");
                stringBuilder.append("nested statement { }");
                System.out.println(stringBuilder);
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.indentationStack.getIndentation());
            }
            System.out.println(this.indentationStack.getIndentation() + "}");
            this.indentationStack.setNestedToZero();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.indentationStack.getIndentation());
            stringBuilder.append("}");
            System.out.println(stringBuilder);
        }
        try {
            this.indentationStack.pop(IndentationType.method);
        } catch (IndentationStackException ignored) {}
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#constructor}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterConstructor(JythonParser.ConstructorContext ctx) {
        try {
            this.indentationStack.push(IndentationType.concstructor);
        } catch (IndentationStackException ignored) {}

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.indentationStack.getIndentation());
        stringBuilder.append("class constructor: ");
        stringBuilder.append(ctx.cosntructorType.getText());
        stringBuilder.append("{");
        System.out.println(stringBuilder);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#constructor}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitConstructor(JythonParser.ConstructorContext ctx) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.indentationStack.getIndentation());
        stringBuilder.append("}");
        System.out.println(stringBuilder);
        try {
            this.indentationStack.pop(IndentationType.concstructor);
        } catch (IndentationStackException ignored) {}
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#parameter}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterParameter(JythonParser.ParameterContext ctx) {
        try {
            this.indentationStack.push(IndentationType.parameters);
        } catch (IndentationStackException ignored) {}

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.indentationStack.getIndentation());
        stringBuilder.append("parameter list: ");
        stringBuilder.append("[");

        for (ParseTree child : ctx.children) {
            int childCount = child.getChildCount();
            if (childCount > 0) {
                for (int i = 0; i < childCount; i++) {
                    stringBuilder.append(child.getChild(i).getText());
                    if (i == 0) {
                        stringBuilder.append(" ");
                    }
                }
                stringBuilder.append(", ");
            }
        }
        System.out.print(stringBuilder);
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#parameter}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitParameter(JythonParser.ParameterContext ctx) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("]");
        System.out.println(stringBuilder);
        try {
            this.indentationStack.pop(IndentationType.parameters);
        } catch (IndentationStackException ignored) {
        }
    }

    /**
     * Enter a parse tree produced by {@link JythonParser#statement}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterStatement(JythonParser.StatementContext ctx) {

        if (ctx.for_statment() != null ||
                ctx.while_statment() != null ||
                ctx.if_statment() != null ||
                ctx.if_else_statment() != null) {
            this.indentationStack.increaseNestedByOne();
        }
        if (ctx.varDec() != null && this.indentationStack.getNestedCounter() == 0) {
            try {
                this.indentationStack.push(IndentationType.statement);
            } catch (IndentationStackException ignored) {}

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.indentationStack.getIndentation());
            stringBuilder.append("field: ");
            stringBuilder.append(ctx.varDec().varibaleName.getText());
            stringBuilder.append("/ type: ");
            stringBuilder.append(ctx.varDec().variableType.getText());
            System.out.println(stringBuilder);
        }
    }

    /**
     * Exit a parse tree produced by {@link JythonParser#statement}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitStatement(JythonParser.StatementContext ctx) {
        try {
            this.indentationStack.pop(IndentationType.statement);
        } catch (IndentationStackException ignored) {}
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
     * Enter a parse tree produced by {@link JythonParser#class_instance}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterClass_instance(JythonParser.Class_instanceContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser#class_instance}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitClass_instance(JythonParser.Class_instanceContext ctx) {

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

    /**
     * Enter a parse tree produced by {@link JythonParser}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterExp(JythonParser.ExpContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitExp(JythonParser.ExpContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link JythonParser}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterPrefixexp(JythonParser.PrefixexpContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link JythonParser}.
     *
     * @param ctx the parse tree
     */
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

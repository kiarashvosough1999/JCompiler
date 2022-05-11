package Indentation;

import java.util.Stack;

public class IndentationStack {

    private final Stack<Indentable> indentablesStack;

    private Integer nestedCounter;

    public IndentationStack() {
        this.indentablesStack = new Stack<>();
        this.nestedCounter = 0;
    }

    public Integer getNestedCounter() {
        return nestedCounter;
    }

    public void increaseNestedByOne() {
        this.nestedCounter += 1;
    }

    public void decreaseNestedByOne() {
        this.nestedCounter -= 1;
    }

    public void setNestedToZero() {
        this.nestedCounter = 0;
    }

    public String getIndentation() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Indentable indentable :indentablesStack) {
            stringBuilder.append(indentable.indentationLiteral());
        }
        return stringBuilder.toString();
    }

    public void pop(IndentationType type) throws IndentationStackException {
        if (this.indentablesStack.peek().getIndentationType() != type) {
            throw new IndentationStackException(type, this.indentablesStack.peek().getIndentationType());
        } else {
            this.indentablesStack.pop();
        }
    }

    public void push(IndentationType type) throws IndentationStackException {
        if (this.indentablesStack.size() > 0 && this.indentablesStack.peek().getIndentationType() == type) {
            throw new IndentationStackException(type);
        }
        else {
            switch (type) {
                case program -> this.indentablesStack.push(new ProgramIndentation());

                case importt -> this.indentablesStack.push(new ImportIndentation());

                case classs -> this.indentablesStack.push(new ClassIndentation());

                case variable -> this.indentablesStack.push(new VariableIndentation());

                case array -> this.indentablesStack.push(new ArrayIndentation());

                case concstructor -> this.indentablesStack.push(new ConcstructorIndentation());

                case parameters -> this.indentablesStack.push(new ParametersIndentation());

                case method -> this.indentablesStack.push(new MethodIndentation());

                case statement -> this.indentablesStack.push(new StatementIndentation());
            }
        }
    }
}

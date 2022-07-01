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

package ProgramPrinter.Indentation;

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

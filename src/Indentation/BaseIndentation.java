package Indentation;

public abstract class BaseIndentation implements Indentable {
    protected Integer indentaionCount;
    protected IndentationType type;

    public BaseIndentation(Integer indentaionCount, IndentationType type) {
        this.indentaionCount = indentaionCount;
        this.type = type;
    }

    @Override
    public String indentationLiteral() {
        return "    ".repeat(Math.max(0, this.indentaionCount));
    }

    @Override
    public Integer getIndentationCount() {
        return this.indentaionCount;
    }

    @Override
    public IndentationType getIndentationType() {
        return this.type;
    }
}

package Indentation;

public class IndentationStackException extends Exception {

    public IndentationStackException(IndentationType popType, IndentationType topType) {
        super("invalid " + popType.toString() + " type to pop on the top of stack with type" + topType.toString());
    }

    public IndentationStackException(IndentationType topType) {
        super("invalid " + topType.toString() + " type to push on stack which is already has same type on peek");
    }

}

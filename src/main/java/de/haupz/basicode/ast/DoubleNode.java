package de.haupz.basicode.ast;

import de.haupz.basicode.interpreter.InterpreterState;

public class DoubleNode extends ValueNode {

    private final double value;

    public DoubleNode(double value) {
        this.value = value;
    }

    @Override
    public Object eval(InterpreterState state) {
        return value;
    }

}

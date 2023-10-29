package de.haupz.basicode.interpreter;

import de.haupz.basicode.ast.ProgramNode;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.*;

public class InterpreterState {

    private final ProgramNode program;

    private final PrintStream out;

    private final BufferedReader in;

    private final Map<String, Object> vars = new HashMap<>();

    private final Stack<Integer> callStack = new Stack<>();

    public record For(int startIndex, Number end, Number step, boolean isIntIterator) {}

    private final Map<String, For> runningForLoops = new HashMap<>();

    private int statementIndex = 0;

    private boolean end = false;

    private boolean lineJump = false;

    private boolean ret = false;

    private boolean backedge = false;

    private int lineJumpTarget;

    private int backedgeTarget;

    private int dataPtr = 0;

    public InterpreterState(ProgramNode program, BufferedReader in, PrintStream out) {
        this.program = program;
        this.in = in;
        this.out = out;
    }

    public BufferedReader getInput() {
        return in;
    }

    public PrintStream getOutput() {
        return out;
    }

    public void setVar(String id, Object value) {
        vars.put(id, value);
    }

    public Optional<Object> getVar(String id) {
        return Optional.ofNullable(vars.get(id));
    }

    public void terminate() {
        end = true;
    }

    public boolean shouldEnd() {
        return end;
    }

    public boolean isLineJumpNext() {
        return lineJump;
    }

    public void requestLineJump() {
        lineJump = true;
    }

    public void lineJumpDone() {
        lineJump = false;
    }

    public int getLineJumpTarget() {
        return lineJumpTarget;
    }

    public void requestReturn() {
        ret = true;
    }

    public void returnDone() {
        ret = false;
    }

    public boolean isReturnNext() {
        return ret;
    }

    public void setLineJumpTarget(int target) {
        lineJumpTarget = target;
    }

    public int getStatementIndex() {
        return statementIndex;
    }

    public void incStatementIndex() {
        ++statementIndex;
    }

    public void setNextStatement(int nextStmt) {
        statementIndex = nextStmt;
    }

    public void pushReturnIndex() {
        callStack.push(statementIndex + 1);
    }

    public int getReturnIndex() {
        return callStack.pop();
    }

    public boolean isRunningLoop(String id) {
        return runningForLoops.containsKey(id);
    }

    public void startLoop(String id, Number end, Number step, boolean isIntIterator) {
        runningForLoops.put(id, new For(statementIndex, end, step, isIntIterator));
    }

    public void stopLoop(String id) {
        runningForLoops.remove(id);
    }

    public For getLoop(String id) {
        return runningForLoops.get(id);
    }

    public void requestBackedge() {
        backedge = true;
    }

    public void backedgeDone() {
        backedge = false;
    }

    public boolean isBackedgeNext() {
        return backedge;
    }

    public void setBackedgeTarget(int b) {
        backedgeTarget = b;
    }

    public int getBackedgeTarget() {
        return backedgeTarget;
    }

    public void resetDataPtr() {
        dataPtr = 0;
    }

    public Object readNextDataItem() {
        List<Object> dataList = program.getDataList();
        if (dataPtr >= dataList.size()) {
            throw new IllegalStateException(String.format("read index %d exceeds size %d", dataPtr, dataList.size()));
        }
        return dataList.get(dataPtr++);
    }

}

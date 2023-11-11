package de.haupz.basicode.ast;

import de.haupz.basicode.interpreter.InterpreterState;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class PrintNode extends StatementNode {

    private static final DecimalFormat DECIMAL_FORMAT =
            new DecimalFormat("#.#########", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

    public enum ElementType { EXPRESSION, TAB, SEPARATOR; }

    public record Element(ElementType type, Object payload) {}

    private static final Element SEP_NO_NEWLINE = new Element(ElementType.SEPARATOR, ";");

    private final List<Element> elements;

    public PrintNode(List<Element> elements) {
        this.elements = List.copyOf(elements);
    }

    @Override
    public void run(InterpreterState state) {
        if (elements.isEmpty()) {
            state.getOutput().println();
            state.resetOutputColumn();
            return;
        }
        for (Element e : elements) {
            switch (e.type) {
                case EXPRESSION -> {
                    Object v = ((ExpressionNode) e.payload).eval(state);
                    String s = v instanceof Double d ? DECIMAL_FORMAT.format(d) : v.toString();
                    state.getOutput().print(s);
                    state.increaseOutputColumn(s.length());
                }
                case TAB -> {
                    int tab = ((Number) ((ExpressionNode) e.payload).eval(state)).intValue();
                    int spacesToPrint = tab - state.getCurrentOutputColumn();
                    if (spacesToPrint < 0) {
                        state.getOutput().println();
                        state.resetOutputColumn();
                        state.getOutput().print(String.format("%" + tab + "s", ""));
                        state.increaseOutputColumn(tab);
                    } else if (spacesToPrint > 0) {
                        state.getOutput().print(String.format("%" + spacesToPrint + "s", ""));
                        state.increaseOutputColumn(spacesToPrint);
                    }
                    // no need to print anything for spacesToPrint == 0
                }
                case SEPARATOR -> {
                    String sep = (String) e.payload;
                    if (",".equals(sep)) {
                        state.getOutput().println();
                        state.resetOutputColumn();
                    }
                }
            }
        }
        // if the final element wasn't a ";" separator, we still need to print a new line
        Element last = elements.get(elements.size() - 1);
        if (!SEP_NO_NEWLINE.equals(last)) {
            state.getOutput().println();
            state.resetOutputColumn();
        }
    }

}

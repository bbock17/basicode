package de.haupz.basicode;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class PrintTest extends InterpreterTest {

    @Test
    public void testPrintComma() {
        testInterpreter("""
                10 PRINT 1,2,3
                """, """
                1
                2
                3
                """);
    }

    @Test
    public void testPrintSemicolon() {
        testInterpreter("""
                10 PRINT 1;2;3
                """, "123\n");
    }

    @Test
    public void testPrintSemicolonNoNewLine() {
        testInterpreter("""
                10 PRINT 1;2;3;
                """, "123");
    }

    @Test
    @Disabled
    public void testTab1() {
        testInterpreter("""
                10 PRINT "A";TAB(3);"B"
                """, """
                A  B
                """);
    }

    @Test
    @Disabled
    public void testTab2() {
        testInterpreter("""
                10 PRINT "AB";TAB(3);"B"
                """, """
                AB B
                """);
    }

    @Test
    @Disabled
    public void testTab3() {
        testInterpreter("""
                10 PRINT "ABC";TAB(3);"B"
                """, """
                ABCB
                """);
    }

    @Test
    @Disabled
    public void testTab4() {
        testInterpreter("""
                10 PRINT "ABCD";TAB(3);"B"
                """, """
                ABCD
                   B
                """);
    }

}

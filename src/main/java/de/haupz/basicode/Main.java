package de.haupz.basicode;

import de.haupz.basicode.ast.ProgramNode;
import de.haupz.basicode.interpreter.Configuration;
import de.haupz.basicode.interpreter.InterpreterState;
import de.haupz.basicode.parser.BasicParser;
import de.haupz.basicode.ui.BasicContainer;

import javax.swing.*;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The main entry point for running BASICODE programs. This class, when run, accepts a collection of
 * {@linkplain Configuration command line flags} as well as the name of a BASICODE source file, and will run that file,
 * displaying any output in the BASICODE GUI.
 */
public class Main {

    /**
     * The GUI component.
     */
    static BasicContainer bc;

    /**
     * The main frame for the GUI component.
     */
//    static BasicFrame bf;
    static UserInterface ui;

    /**
     * Run a BASICODE program.
     *
     * @param code the source code to run, as a string.
     * @param configuration the configuration for the interpreter.
     * @throws Throwable in case anything goes wrong.
     */
    public static void run(String code, Configuration configuration) throws Throwable {
        final BasicParser parser = new BasicParser(new StringReader(code));
        ProgramNode prog = parser.program();
        InterpreterState state = new InterpreterState(prog, bc, bc, configuration);
        bc.registerStopKeyHandler(() -> state.terminate());
        prog.run(state);
        state.closeFiles();
    }

    /**
     * Executes a BASICODE program from a file.
     * 
     * @param filename the filename of the BASICODE program
     * @param configuration the configuration for the interpreter
     * @throws Throwable in case anything goes wrong
     */
    public static void execBasiCode(String filename, Configuration configuration) throws Throwable {
            Path path = Paths.get(filename);
            List<String> sourceLines = Files.readAllLines(path);
            String source = sourceLines.stream().collect(Collectors.joining("\n"));
            run(source, configuration);
    }
    
    /**
     * The main method.
     * 
     * @param args commandline arguments
     * @throws Throwable in case anything goes wrong
     */
    public static void main(String[] args) throws Throwable {
        boolean nowait = false;
        boolean nosound = false;
        boolean hold = false;
        String filename = "";
        for (String arg : args) {
            switch (arg) {
                case "-nowait" -> nowait = true;
                case "-nosound" -> nosound = true;
                case "-hold" -> hold = true;
                default -> filename = arg;
            }
        }

        Configuration configuration = new Configuration(nowait, nosound, hold);

        bc = new BasicContainer();
        SwingUtilities.invokeLater(() -> {
//            bf = new BasicFrame(bc);
//            bf.setVisible(true);
            ui = new UserInterface(bc);
            ui.setVisible(true);
        });

        if (!filename.isEmpty()) {
            execBasiCode(filename, configuration);
            System.out.println("done.");
            bc.shutdown();
//        bf.dispose();
//        ui.dispose();
        }
    }

}
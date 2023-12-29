package de.haupz.basicode;

import static de.haupz.basicode.Main.bc;
import de.haupz.basicode.ast.ProgramNode;
import de.haupz.basicode.interpreter.Configuration;
import de.haupz.basicode.interpreter.InterpreterState;
import de.haupz.basicode.parser.BasicParser;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The BasiCode engine.
 *
 * @author Bernd Bock <chef@bernd-bock.de>
 */
public class Engine {
    /**
     * Run a BASICODE program.
     *
     * @param code the source code to run, as a string.
     * @param configuration the configuration for the interpreter.
     * @throws Throwable in case anything goes wrong.
     */
    public void run(String code, Configuration configuration) throws Throwable {
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
    public void execBasiCode(String filename, Configuration configuration) throws Throwable {
            Path path = Paths.get(filename);
            List<String> sourceLines = Files.readAllLines(path);
            String source = sourceLines.stream().collect(Collectors.joining("\n"));
            run(source, configuration);
    }
}

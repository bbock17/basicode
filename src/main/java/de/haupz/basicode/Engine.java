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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.SwingWorker;

/**
 * The BasiCode engine.
 *
 * @author Bernd Bock <chef@bernd-bock.de>
 */
public class Engine extends SwingWorker {
    
    private final Configuration config;
    private final String filename;
    
    /**
     * Constructor.
     * @param filename the filename of the BASICODE program
     * @param config the configuration for the interpreter
     */
    public Engine(String filename, Configuration config) {
        super();
        this.filename = filename;
        this.config = config;
    }
    
    @Override
    protected String doInBackground() throws Exception {
        try {
            execBasiCode();
        }
        catch (Throwable ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    protected void done() {
        // update the GUI after the work is done
        System.out.println("Worker is done");
    }

    /**
     * Run a BasiCode program.
     *
     * @param code the source code to run, as a string.
     * @throws Throwable in case anything goes wrong.
     */
    private void run(String code) throws Throwable {
        final BasicParser parser = new BasicParser(new StringReader(code));
        ProgramNode prog = parser.program();
        InterpreterState state = new InterpreterState(prog, bc, bc, config);
        bc.registerStopKeyHandler(() -> state.terminate());
        prog.run(state);
        state.closeFiles();
    }

    /**
     * Executes a BasiCode program from a file.
     * 
     * @throws Throwable in case anything goes wrong
     */
    private void execBasiCode() throws Throwable {
        Path path = Paths.get(filename);
        List<String> sourceLines = Files.readAllLines(path);
        String source = sourceLines.stream().collect(Collectors.joining("\n"));
        run(source);
    }
}

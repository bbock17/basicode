package de.haupz.basicode;

import de.haupz.basicode.ast.ProgramNode;
import de.haupz.basicode.interpreter.Configuration;
import de.haupz.basicode.interpreter.InterpreterState;
import de.haupz.basicode.ui.BasicFrame;
import de.haupz.basicode.parser.BasicParser;
import de.haupz.basicode.ui.BasicContainer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
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
    static BasicFrame bf;

    /**
     * Let the user pick a {@code .bas} file to run.
     *
     * @return the absolute path to the chosen file, or the empty string in case no file was chosen.
     */
    private static String chooseFile() {
        File dir = new File(System.getProperty("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("BASIC files", "bas");
        JFileChooser fc = new JFileChooser(dir);
        fc.setFileFilter(filter);
        int choice = fc.showOpenDialog(null);
        return choice == JFileChooser.APPROVE_OPTION ? fc.getSelectedFile().getAbsolutePath() : "";
    }

    /**
     * Read a BASIC source file and return its contents.
     *
     * @param filename the file name (possibly an absolute path).
     * @return the contents of the file.
     * @throws IOException in case anything goes wrong with opening and reading the file.
     */
    private static String getSource(String filename) throws IOException {
        Path path = Paths.get(filename);
        List<String> sourceLines = Files.readAllLines(path);
        String source = sourceLines.stream().collect(Collectors.joining("\n"));
        return source;
    }

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

    public static void main(String[] args) throws Throwable {
        boolean nowait = false;
        boolean nosound = false;
        boolean hold = false;
        boolean enforceBoundaries = false;
        String filename = "";
        for (String arg : args) {
            switch (arg) {
                case "-nowait" -> nowait = true;
                case "-nosound" -> nosound = true;
                case "-hold" -> hold = true;
                case "-enforceBoundaries" -> enforceBoundaries = true;
                default -> filename = arg;
            }
        }

        Configuration configuration = new Configuration(nowait, nosound, hold, enforceBoundaries);

        if (filename.isEmpty()) {
            filename = chooseFile();
        }

        bc = new BasicContainer();
        bf = new BasicFrame(bc);

        if (!filename.isEmpty()) {
            String source = getSource(filename);
            SwingUtilities.invokeLater(() -> {
                bf.setVisible(true);
            });
            run(source, configuration);
        }

        bc.shutdown();
        bf.dispose();
    }

}
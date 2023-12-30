package de.haupz.basicode;

import de.haupz.basicode.interpreter.Configuration;
import de.haupz.basicode.ui.BasicContainer;

import javax.swing.*;

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
     * The main method.
     * 
     * @param args commandline arguments
     * @throws Throwable in case anything goes wrong
     */
    public static void main(String[] args) throws Throwable {
        Parameters params = new Parameters();
        
        for (String arg : args) {
            switch (arg) {
                case "-nowait"  -> params.setNoWait(true);
                case "-nosound" -> params.setNoSound(true);
                case "-hold"    -> params.setHold(true);
                default         -> params.setFilename(arg);
            }
        }

        bc = new BasicContainer();
        SwingUtilities.invokeLater(() -> {
//            bf = new BasicFrame(bc);
//            bf.setVisible(true);
            ui = new UserInterface(bc, params);
            ui.setVisible(true);
        });
/*
        if (!filename.isEmpty()) {
            Configuration configuration = new Configuration(nowait, nosound, hold);
            Engine engine = new Engine();
            engine.execBasiCode(filename, configuration);
            System.out.println("done.");
            bc.shutdown();
//        bf.dispose();
//        ui.dispose();
        }
    }
*/
    }
}

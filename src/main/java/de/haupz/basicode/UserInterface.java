package de.haupz.basicode;

import static de.haupz.basicode.Main.bc;
import de.haupz.basicode.interpreter.Configuration;
import de.haupz.basicode.ui.BasicContainer;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Graphical User Interface of basicode.
 * 
 * @author Bernd Bock <chef@bernd-bock.de>
 */
public class UserInterface extends javax.swing.JFrame {

    private static final String VERSION = "1.0";
    
    private final Parameters params;
    
    /**
     * Creates new form UserInterface.
     * @param bc BasicContainer
     * @param params basicode parameters from the commandline
     */
    public UserInterface(BasicContainer bc, Parameters params) {
        super("BASICODE");
        this.params = params;
        initComponents();
        initConfig();
        Container cp = getContentPane();
        cp.add(bc);
        Dimension size = new Dimension(BasicContainer.WIDTH + 16, BasicContainer.HEIGHT + jMenuBar1.getHeight() + 39);
        setSize(size);
        addKeyListener(bc.makeKeyListener());
    }
    
    /**
     * Initializes form elements according to the paramters.
     */
    private void initConfig() {
        menuItemNoWait.setSelected(params.isNoWait());
        menuItemNoSound.setSelected(params.isNoSound());
        menuItemHold.setSelected(params.isHold());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuItemOpen = new javax.swing.JMenuItem();
        menuItemExit = new javax.swing.JMenuItem();
        menuConfiguration = new javax.swing.JMenu();
        menuItemNoWait = new javax.swing.JCheckBoxMenuItem();
        menuItemNoSound = new javax.swing.JCheckBoxMenuItem();
        menuItemHold = new javax.swing.JCheckBoxMenuItem();
        menuHelp = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuFile.setMnemonic('F');
        menuFile.setText("File");

        menuItemOpen.setMnemonic('O');
        menuItemOpen.setText("Open");
        menuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenActionPerformed(evt);
            }
        });
        menuFile.add(menuItemOpen);

        menuItemExit.setMnemonic('X');
        menuItemExit.setText("Exit");
        menuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExitActionPerformed(evt);
            }
        });
        menuFile.add(menuItemExit);

        jMenuBar1.add(menuFile);

        menuConfiguration.setMnemonic('C');
        menuConfiguration.setText("Configuration");

        menuItemNoWait.setMnemonic('W');
        menuItemNoWait.setText("No wait");
        menuConfiguration.add(menuItemNoWait);

        menuItemNoSound.setMnemonic('S');
        menuItemNoSound.setText("No sound");
        menuConfiguration.add(menuItemNoSound);

        menuItemHold.setMnemonic('H');
        menuItemHold.setSelected(true);
        menuItemHold.setText("Hold");
        menuConfiguration.add(menuItemHold);

        jMenuBar1.add(menuConfiguration);

        menuHelp.setMnemonic('H');
        menuHelp.setText("Help");

        jMenuItem2.setMnemonic('A');
        jMenuItem2.setText("About");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuHelp.add(jMenuItem2);

        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 814, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOpenActionPerformed
        JFileChooser fc = getFileChooser();
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            boolean nowait  = menuItemNoWait.isSelected();
            boolean nosound = menuItemNoSound.isSelected();
            boolean hold    = menuItemHold.isSelected();
            Configuration configuration = new Configuration(nowait, nosound, hold);
            
            Engine engine = new Engine(selectedFile.getPath(), configuration);
            engine.execute();
            
            bc.shutdown();
    //        bf.dispose();
    //        ui.dispose();
        }
    }//GEN-LAST:event_menuItemOpenActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JOptionPane.showMessageDialog(this, "Java-BasiCode\nVersion " + VERSION + "\n\nDr. Michael Haupt", "About Java-BasiCode", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItemExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenu menuConfiguration;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JCheckBoxMenuItem menuItemHold;
    private javax.swing.JCheckBoxMenuItem menuItemNoSound;
    private javax.swing.JCheckBoxMenuItem menuItemNoWait;
    private javax.swing.JMenuItem menuItemOpen;
    // End of variables declaration//GEN-END:variables

    private JFileChooser fileChooser = null;
    
    /**
     * Gets the file chooser dialog. On first use sets the filter to BAS files
     * and sets the current directory according to the configuration.
     * 
     * @return file chooser dialog
     */
    private JFileChooser getFileChooser() {
        if (fileChooser == null) {
            fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileFilter filter = new FileNameExtensionFilter("BASICODE programs (*.bas)", "bas");
            fileChooser.addChoosableFileFilter(filter);
            fileChooser.setFileFilter(filter);
            fileChooser.setDialogTitle("Open BASICODE file");
            
//            if (config.getSvgPath() != null) {
            fileChooser.setCurrentDirectory(new File("D:\\Temp")); // TODO current directory
//            }
        }
        
        return fileChooser;
    }
}

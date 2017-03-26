/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto_sys;

/**
 *
 * @author arjun
 */import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileFilter;

/**
This code uses a JList in two forms (layout orientation vertical & horizontal wrap) to
display a File[].  The renderer displays the file icon obtained from FileSystemView.
*/
class FileList {

    public Component getGui(File[] all, boolean vertical) {
        // put File objects in the list..
        JList fileList = new JList(all);
        // ..then use a renderer
        fileList.setCellRenderer(new FileRenderer(!vertical));

        if (!vertical) {
            fileList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
            fileList.setVisibleRowCount(-1);
        } else {
            fileList.setVisibleRowCount(9);
        }
        return new JScrollPane(fileList);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                File f = new File(System.getProperty("user.home"));
                FileList fl = new FileList();
                Component c1 = fl.getGui(f.listFiles(new TextFileFilter()),true);

                //f = new File(System.getProperty("user.home"));
                Component c2 = fl.getGui(f.listFiles(new TextFileFilter()),false);

                JFrame frame = new JFrame("File List");
                JPanel gui = new JPanel(new BorderLayout());
                gui.add(c1,BorderLayout.WEST);
                gui.add(c2,BorderLayout.CENTER);
                c2.setPreferredSize(new Dimension(375,100));
                gui.setBorder(new EmptyBorder(3,3,3,3));

                frame.setContentPane(gui);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class TextFileFilter implements FileFilter {

    public boolean accept(File file) {
        // implement the logic to select files here..
        String name = file.getName().toLowerCase();
        //return name.endsWith(".java") || name.endsWith(".class");
        return name.length()<20;
    }
}

class FileRenderer extends DefaultListCellRenderer {

    private boolean pad;
    private Border padBorder = new EmptyBorder(3,3,3,3);

    FileRenderer(boolean pad) {
        this.pad = pad;
    }

    @Override
    public Component getListCellRendererComponent(
        JList list,
        Object value,
        int index,
        boolean isSelected,
        boolean cellHasFocus) {

        Component c = super.getListCellRendererComponent(
            list,value,index,isSelected,cellHasFocus);
        JLabel l = (JLabel)c;
        File f = (File)value;
        l.setText(f.getName());
        l.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f));
        if (pad) {
            l.setBorder(padBorder);
        }

        return l;
    }
}
/*public class User_Files extends javax.swing.JFrame {

    
     * Creates new form User_Files
     
    public User_Files() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(User_Files.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_Files.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_Files.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_Files.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User_Files().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
*/
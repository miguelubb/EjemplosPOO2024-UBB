package ejemplos.gui.menutexto;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class EditorArchivo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lbNombreArchivo;
    private JEditorPane editorTexto;
    private String archivo;

    public EditorArchivo(String archivo) {
        this.archivo = archivo;
        setSize(800,600);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        try {
            File file = new File(archivo);
            if (file.exists()) {
                lbNombreArchivo.setText(file.getAbsolutePath());
                List<String> texto = Files.readAllLines(Path.of(archivo));
                editorTexto.setText(String.join("\n", texto));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });



        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onOK() {
        // guardar el archivo..
        try {
            Files.writeString(Path.of(archivo), editorTexto.getText());
            JOptionPane.showMessageDialog(null, "Archivo guardado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void display(String archivo) {
        EditorArchivo dialog = new EditorArchivo(archivo);
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivo() {
        return archivo;
    }
}

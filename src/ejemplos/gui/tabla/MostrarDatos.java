package ejemplos.gui.tabla;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class MostrarDatos extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tablaClientes;
    private JButton obtenerDeudaButton;

    public MostrarDatos() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        tablaClientes.setModel(new DefaultTableModel());

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
        obtenerDeudaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtenerDeuda();
            }
        });
    }

    private void obtenerDeuda() {
        int fila=tablaClientes.getSelectedRow();
        String rut=tablaClientes.getModel().getValueAt(fila,0).toString();
        String nombre=tablaClientes.getModel().getValueAt(fila,1).toString();

        double deuda=ListaClientes.getInstance().obtenerDeuda(rut);
        JOptionPane.showMessageDialog(null,
                "Deuda de "+nombre+" es: "+deuda,
                "Informacion de deuda",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void onOK() {
        // add your code here
        String[][] data=ListaClientes.getInstance().listadoClientes();
        String[] colum={"RUT","NOMBRE", "EDAD"};
        tablaClientes.setModel(new DefaultTableModel(data, colum));
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        MostrarDatos dialog = new MostrarDatos();
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

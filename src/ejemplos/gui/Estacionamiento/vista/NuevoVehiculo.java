package ejemplos.gui.Estacionamiento.vista;

import ejemplos.gui.Estacionamiento.controlador.Controlador;

import javax.swing.*;
import java.awt.event.*;

public class NuevoVehiculo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfPatente;
    private JTextField tfMarca;
    private JTextField tfModelo;
    private JTextField tfColor;

    public NuevoVehiculo(JTextField parent) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        tfPatente.setText(parent.getText());
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
        // add your code here
        String patente=tfPatente.getText();
        String marca=tfMarca.getText();
        String modelo=tfModelo.getText();
        String color=tfColor.getText();
        boolean ok= Controlador.getInstance().nuevoVehiculo(patente,marca,modelo,color);
        if(ok){
            JOptionPane.showMessageDialog(this, "Vehículo guardados exitosamente","Informacion", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }else{
            JOptionPane.showMessageDialog(this, "Error al guardar vehiculo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void display(JTextField parent) {
        NuevoVehiculo dialog = new NuevoVehiculo(parent);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}

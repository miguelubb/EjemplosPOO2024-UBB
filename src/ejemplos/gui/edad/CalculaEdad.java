package ejemplos.gui.edad;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.Period;

public class CalculaEdad extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfFechaNacimiento;
    private JLabel lbOutput;
    private JLabel lbErrorFecha;
    private JPanel panel1;
    private JLabel lbTorta;

    public CalculaEdad() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);




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
        try {
            String[] fecha = tfFechaNacimiento.getText().split("/");
            LocalDate hoy = LocalDate.now();
            LocalDate cumple = LocalDate.of(Integer.parseInt(fecha[2]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[0]));
            Period p = Period.between(cumple, hoy);
            String resp = String.format("La persona tiene: %,d años con %d meses y %d días de edad.", p.getYears(), p.getMonths(), p.getDays());
            lbOutput.setText(resp);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "La fecha ingresada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CalculaEdad dialog = new CalculaEdad();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }


}

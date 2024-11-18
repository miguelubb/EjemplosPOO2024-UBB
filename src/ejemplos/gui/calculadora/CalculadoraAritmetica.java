package ejemplos.gui.calculadora;

import javax.swing.*;
import java.awt.event.*;

public class CalculadoraAritmetica extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfA;
    private JComboBox cbOper;
    private JTextField tfB;
    private JLabel lbResultado;

    public CalculadoraAritmetica() {
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
        tfA.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                onOK();
            }
        });
        cbOper.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                onOK();
            }
        });

        tfB.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        try {
            Double A = Double.parseDouble(tfA.getText());
            Double B = Double.parseDouble(tfB.getText());
            String oper = cbOper.getSelectedItem().toString();
            Double res;
            switch (oper) {
                case "+":
                    res = A + B;
                    lbResultado.setText("" + res);
                    break;
                case "-":
                    res = A - B;
                    lbResultado.setText("" + res);
                    break;
                case "*":
                    res = A * B;
                    lbResultado.setText("" + res);
                    break;
                case "/":
                    res = A / B;
                    lbResultado.setText("" + res);
                    break;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor valido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CalculadoraAritmetica dialog = new CalculadoraAritmetica();
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

package ejemplos.gui.login;

import javax.swing.*;
import java.awt.event.*;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfUser;
    private JPasswordField pfPwd;

    public Login() {
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
        /*
        Si el usaurio es "admin" y la contraseña es "admin",
            cerrar ventana de login y desplegar mensaje "Bienvenido al sistema",
        sino "Login o contraseña no válida"
        */
        String user = tfUser.getText();
        String pwd = new String(pfPwd.getPassword());
        if(user.equals("admin") && pwd.equals("admin")) {
            JOptionPane.showMessageDialog(this,"Bienvenido al sistema", "Informacion", JOptionPane.INFORMATION_MESSAGE );
            dispose();//cerrar la ventana, destruyéndola.
        }else{
            JOptionPane.showMessageDialog(this, "Usuario o Contraseña no válida","Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Login dialog = new Login();
        dialog.pack();
        //centrar a la pantalla
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
}

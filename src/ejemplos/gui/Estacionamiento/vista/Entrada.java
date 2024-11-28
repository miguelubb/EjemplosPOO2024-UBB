package ejemplos.gui.Estacionamiento.vista;

import ejemplos.gui.Estacionamiento.controlador.Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Entrada extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField patenteEntrada;
    private JButton entradaButton;
    private JTextField patenteSalida;
    private JButton salidaButton;
    private JTable tablaTransacciones;
    private JTextField tfMonto;
    private JButton pagarButton;
    private JButton sumaDePagosButton;

    public Entrada() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        actualizarTabla();


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

        entradaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patente=patenteEntrada.getText();
                if(!Controlador.getInstance().existeVehiculo(patente)) {
                    JOptionPane.showMessageDialog(Entrada.this, "El vehiculo No existe. Lo debe crear primero","Error",JOptionPane.ERROR_MESSAGE);
                    NuevoVehiculo.display(patenteEntrada);
                    return;
                }
                int valorMinuto=Controlador.getInstance().getValorMinuto();
                boolean isOk=Controlador.getInstance().entrada(patente, valorMinuto);
                if(isOk){
                    //actualizar la tabla
                    actualizarTabla();
                    JOptionPane.showMessageDialog(entradaButton, "Vehículo ingresado con éxito");
                }else{
                    JOptionPane.showMessageDialog(entradaButton, "NO es posible realizar el ingreso");
                }
                entradaButton.setEnabled(false);
            }
        });
        salidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patente=patenteSalida.getText();
                boolean isOk=Controlador.getInstance().salida(patente);
                if(isOk){
                    actualizarTabla();
                }else{
                    JOptionPane.showMessageDialog(salidaButton, "No es posible hacer la salida");
                }
            }
        });
        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int monto=Integer.parseInt(tfMonto.getText());
                int num=tablaTransacciones.getSelectedRow();
                //para seleccion múltiple
                if(num==-1) {
                    JOptionPane.showMessageDialog(null, "seleccione primero una fila");
                }else {
                    boolean isOK = Controlador.getInstance().pagar(num, monto);
                    if (isOK) {
                        actualizarTabla();
                        JOptionPane.showMessageDialog(null, "Pago realizado ver tabla");
                    } else {
                        JOptionPane.showMessageDialog(null, "NO se pudo realizar el pago, seleccione la transacción correcta");
                    }
                }
            }
        });
        sumaDePagosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //obtener las filas seleccionadas
                int[] filas=tablaTransacciones.getSelectedRows();
                int sum=0;
                for (int fila : filas) {
                    sum+=Integer.parseInt((String)tablaTransacciones.getValueAt(fila,3));
                }
                JOptionPane.showMessageDialog(null, "Suma: "+sum);
            }
        });
        patenteEntrada.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(patenteEntrada.getText()!=null){
                    entradaButton.setEnabled(true);
                }
            }
        });
        patenteEntrada.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(patenteEntrada.getText()!=null){
                    entradaButton.setEnabled(true);
                }
            }
        });
    }

    private void actualizarTabla() {
        String[][] data= Controlador.getInstance().transacciones();
        String[] colum={"Entrada","Salida","Valor a pagar","Monto pagado","Patente"};
        tablaTransacciones.setModel(new DefaultTableModel(data, colum));

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void display(){
        Entrada dialog = new Entrada();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

}

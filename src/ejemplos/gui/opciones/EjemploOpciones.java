package ejemplos.gui.opciones;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class EjemploOpciones extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton hamburguesaRadioButton;
    private JRadioButton completoRadioButton;
    private JRadioButton pizzaRadioButton;
    private JRadioButton fajitaRadioButton;
    private JRadioButton burritoRadioButton;
    private JRadioButton churrascoRadioButton;
    private JCheckBox panCheckBox;
    private JCheckBox quesoCheckBox;
    private JCheckBox lechugaCheckBox;
    private JCheckBox mayonesaCheckBox;
    private JCheckBox paltaCheckBox;
    private JCheckBox mostazaCheckBox;
    private String pedido;
    private ArrayList<String> opciones=new ArrayList<>();

    public EjemploOpciones() {
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
        panCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(panCheckBox.isSelected()){
                    opciones.add("Pan");
                }else{
                    opciones.remove("Pan");
                }
            }
        });
        quesoCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(quesoCheckBox.isSelected()){
                    opciones.add("Queso");
                }else{
                    opciones.remove("Queso");
                }
            }
        });
        lechugaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lechugaCheckBox.isSelected()){
                    opciones.add("Lichuga");
                }else{
                    opciones.remove("Lichuga");
                }
            }
        });
        mayonesaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mayonesaCheckBox.isSelected()){
                    opciones.add("Mayonesa");
                }else{
                    opciones.remove("Mayonesa");
                }
            }
        });
        paltaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(paltaCheckBox.isSelected()){
                    opciones.add("Palta");
                }else {
                    opciones.remove("Palta");
                }
            }
        });
        mostazaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mostazaCheckBox.isSelected()){
                    opciones.add("Mostaza");
                }else{
                    opciones.remove("Mostaza");
                }
            }
        });
        hamburguesaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pedido="Hamburguesa";
            }
        });
        completoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pedido="Completo";
            }
        });
        pizzaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pedido="Pizza";
            }
        });
        fajitaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pedido="Fajita";
            }
        });
        burritoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pedido="Burrito";
            }
        });
        churrascoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pedido="Churrasco";
            }
        });
    }

    private void onOK() {
       JOptionPane.showMessageDialog(this,pedido+opciones.toString(),"Pedido del cliente", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        EjemploOpciones dialog = new EjemploOpciones();
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

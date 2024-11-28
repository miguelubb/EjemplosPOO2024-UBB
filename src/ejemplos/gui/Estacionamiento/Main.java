package ejemplos.gui.Estacionamiento;

import ejemplos.gui.Estacionamiento.controlador.Controlador;
import ejemplos.gui.Estacionamiento.vista.Entrada;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Controlador.getInstance().nuevoVehiculo("AA0001","Nisan", "V16", "Blanco");
        Controlador.getInstance().nuevoVehiculo("AA0002","Nisan", "V16", "Blanco");
        Controlador.getInstance().nuevoVehiculo("AA0003","Nisan", "V16", "Blanco");

        Entrada.display();

    }
}

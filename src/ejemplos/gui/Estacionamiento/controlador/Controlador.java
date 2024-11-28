package ejemplos.gui.Estacionamiento.controlador;

import ejemplos.gui.Estacionamiento.modelo.EntradaSalida;
import ejemplos.gui.Estacionamiento.modelo.Vehiculo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class Controlador {
    //1) variable de instancia estática que guarde el controlador
    private static Controlador instance = new Controlador();

    public static Controlador getInstance() {
        return instance;
    }

    private Controlador() {
        capacidad = 20;
        valorMinuto = 30;
        vehiculos = new ArrayList<>();
        entradasYSalidas = new ArrayList<>();
    }

    private int capacidad, valorMinuto;

    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<EntradaSalida> entradasYSalidas;

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getValorMinuto() {
        return valorMinuto;
    }

    public void setValorMinuto(int valorMinuto) {
        this.valorMinuto = valorMinuto;
    }

    public int disponibilidad() {
        int ocupacion = (int) entradasYSalidas.stream()
                .filter(e -> e.getSalida() == null)
                .count();
        return capacidad - ocupacion;
    }

    public boolean nuevoVehiculo(String patente, String marca, String modelo, String color) {
        Vehiculo nuevo = new Vehiculo(patente, marca, modelo, color);
        if (!vehiculos.contains(nuevo)) {
            return vehiculos.add(nuevo);
        }
        return false;
    }

    public boolean entrada(String patente, int valorMinuto) {
        LocalDateTime entrada = LocalDateTime.now();
        Optional<Vehiculo> opVehiculo = vehiculos.stream()
                .filter(e -> e.getPatente().equals(patente))
                .findFirst();
        if (opVehiculo.isPresent()) {
            Optional<EntradaSalida> transaccion = entradasYSalidas.stream()
                    .filter(t -> t.getVehiculo().getPatente().equals(patente)
                            && t.getSalida() == null)
                    .findFirst();
            if (!transaccion.isPresent()) {
                EntradaSalida nuevo = new EntradaSalida(entrada, valorMinuto, opVehiculo.get());
                if (!entradasYSalidas.contains(nuevo)) {
                    entradasYSalidas.add(nuevo);
                    opVehiculo.get().addEntradaYSalida(nuevo);
                    return true;
                }

            }
            return false;
        } else {
            return false;
        }
    }

    public boolean salida(String patente) {
        Optional<EntradaSalida> transaccion = entradasYSalidas.stream()
                .filter(t -> t.getVehiculo().getPatente().equals(patente)
                        && t.getSalida() == null)
                .findFirst();
        if (transaccion.isPresent()) {
            LocalDateTime horaSalida = LocalDateTime.now();
            transaccion.get().setSalida(horaSalida);
            transaccion.get().setMontoPagado(0);
            return true;
        }
        return false;
    }

    public boolean pagar(int numTransaccion, int monto){
        if(numTransaccion>=0 && numTransaccion<entradasYSalidas.size()) {
            EntradaSalida trans = entradasYSalidas.get(numTransaccion);
            trans.setMontoPagado(monto);
            return true;
        }
        return false;
    }

    public String[][] transacciones(){
        String[][] out=new String[entradasYSalidas.size()][5];
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        for (int i = 0; i < entradasYSalidas.size(); i++) {
            EntradaSalida t=entradasYSalidas.get(i);
            out[i][0]=t.getEntrada().format(dtf);
            out[i][1]=t.getSalida()==null?"-":t.getSalida().format(dtf);
            out[i][2]= String.valueOf(t.getTotalAPagar());
            out[i][3]= String.valueOf(t.getMontoPagado());
            out[i][4]=t.getVehiculo().getPatente();
        }
        return out;
    }
    public boolean existeVehiculo(String patente){
        Vehiculo out=vehiculos.parallelStream().filter(e -> e.getPatente().equals(patente)).findAny().orElse(null);
        return out!=null;
    }
}

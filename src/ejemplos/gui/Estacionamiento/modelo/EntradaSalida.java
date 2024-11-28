package ejemplos.gui.Estacionamiento.modelo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class EntradaSalida {
    private LocalDateTime entrada, salida;
    private int valorMinuto, montoPagado;
    private Vehiculo vehiculo;

    public EntradaSalida(LocalDateTime entrada, int valorMinuto,
                         Vehiculo vehiculo) {
        this.entrada = entrada;
        this.valorMinuto = valorMinuto;
        salida=null;
        montoPagado=0;
        this.vehiculo=vehiculo;

    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    public void setSalida(LocalDateTime salida) {
        this.salida = salida;
    }

    public int getValorMinuto() {
        return valorMinuto;
    }

    public void setValorMinuto(int valorMinuto) {
        this.valorMinuto = valorMinuto;
    }

    public int getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(int montoPagado) {
        this.montoPagado = montoPagado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntradaSalida that = (EntradaSalida) o;
        return Objects.equals(entrada, that.entrada) && Objects.equals(vehiculo, that.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entrada, vehiculo);
    }

    @Override
    public String toString() {
        return "EntradaSalida{" +
                "entrada=" + entrada +
                ", salida=" + salida +
                ", valorMinuto=" + valorMinuto +
                ", montoPagado=" + montoPagado +
                ", vehiculo=" + vehiculo +
                '}';
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public int getTotalAPagar(){
        LocalDateTime out=salida;
        if(salida==null){
            return 0;
        }
        Duration d= Duration.between(entrada, out);
       // return (int) (d.toMinutes()*valorMinuto);
        return (int) (d.toSeconds()*valorMinuto);//para ver un monto a pagar en las puebas
    }
}

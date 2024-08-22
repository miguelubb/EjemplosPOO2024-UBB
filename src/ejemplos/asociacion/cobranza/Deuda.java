package ejemplos.asociacion.cobranza;

import java.time.LocalDate;
import java.util.Objects;

public class Deuda {
    //implementación de asociación con cliente:
    //una deuda pertenece a un único cliente
    private Cliente cliente;

    private String motivo;
    private LocalDate fecha;
    private int monto;

    public Deuda(Cliente cliente, String motivo, LocalDate fecha, int monto) {
        this.motivo = motivo;
        this.fecha = fecha;
        this.monto = monto;
        //deuda se preocupa de la integridad referencial
        this.cliente = cliente;
        cliente.addDeuda(this);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        //eliminar deuda cliente actual
        this.cliente.eliminarDeuda(this);
        //actualizar el cliente
        this.cliente = cliente;
        //actualizar la lista de deudas del cliente nuevo
        cliente.addDeuda(this);
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deuda deuda = (Deuda) o;
        return monto == deuda.monto && Objects.equals(cliente, deuda.cliente) && Objects.equals(motivo, deuda.motivo) && Objects.equals(fecha, deuda.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, motivo, fecha, monto);
    }

    @Override
    public String toString() {
        return "Deuda{" +
                "cliente=" + cliente +
                ", motivo='" + motivo + '\'' +
                ", fecha=" + fecha +
                ", monto=" + monto +
                '}';
    }
}

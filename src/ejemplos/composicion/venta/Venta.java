package ejemplos.composicion.venta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venta {
    private int id;
    private LocalDate fecha;
    private LocalTime hora;
    private String vendedor;

    //asociación con cliente
    private Cliente cliente;

    //asociación con DetalleProducto
    private List<DetalleVenta> detalles = new ArrayList<>();

    public Venta(int id, LocalDate fecha, LocalTime hora, String vendedor, Cliente cliente) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.vendedor = vendedor;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venta venta)) return false;
        return id == venta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", vendedor='" + vendedor + '\'' +
                ", cliente=" + cliente +
                ", detalles=" + detalles +
                '}';
    }

    public double total() {
        double suma = 0.0;
        for (DetalleVenta d : detalles) {
            suma += d.getCantidad() * d.getPrecio()
                    * (1 - d.getFactorDesc());
        }
        return suma;
    }
    //pre: p != null y cantidad > 0
    public void addProducto(Producto p, double cantidad) {
        DetalleVenta detalle=new DetalleVenta(cantidad, this, p);
        detalles.add(detalle);
    }

    public int numDetalles() {
        return detalles.size();
    }
}

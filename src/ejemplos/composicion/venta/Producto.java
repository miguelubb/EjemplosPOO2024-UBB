package ejemplos.composicion.venta;

import java.util.*;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double stockActual;
    private double precioActual;
    private double descuentoActual;

    //asociación con DetalleProducto
    private List<DetalleVenta> detalles=new ArrayList<>();

    public Producto(int id, String nombre, String descripcion, double precioActual, double descuentoActual, double stockActual) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioActual = precioActual;
        this.descuentoActual = descuentoActual;
        this.stockActual = stockActual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getStockActual() {
        return stockActual;
    }

    public void setStockActual(double stockActual) {
        this.stockActual = stockActual;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }

    public double getDescuentoActual() {
        return descuentoActual;
    }

    public void setDescuentoActual(double descuentoActual) {
        this.descuentoActual = descuentoActual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto producto)) return false;
        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", stockActual=" + stockActual +
                ", precioActual=" + precioActual +
                ", descuentoActual=" + descuentoActual +
                '}';
    }
}

package ejemplos.mvcconpresistencia.inventario.modelo;

import java.io.Serializable;
import java.util.*;

public class Producto implements Serializable {
    static final long serialVersionUID = 1L;
    private String sku;
    private String nombre;
    private long precio;
    private long stock;
    private long stockMin;
    public Producto(String sku, String nombre, long precio, long stock, long stockMin) {
        this.sku = sku;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.stockMin = stockMin;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public long getStockMin() {
        return stockMin;
    }

    public void setStockMin(long stockMin) {
        this.stockMin = stockMin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(sku, producto.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sku);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Producto{");
        sb.append("sku='").append(sku).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", precio=").append(precio);
        sb.append(", stock=").append(stock);
        sb.append(", stockMin=").append(stockMin);
        sb.append('}');
        return sb.toString();
    }
}

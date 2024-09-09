package ejemplos.composicion.venta;

public class DetalleVenta {
    private double precio;
    private double cantidad;
    private double factorDesc;

    //asociación Producto
    private Producto producto;
    //asociación con Venta
    private Venta venta;
    public DetalleVenta(double cantidad, Venta v, Producto p) {
        this.precio = p.getPrecioActual();
        this.cantidad = cantidad;
        this.factorDesc = p.getDescuentoActual();
        this.producto = p;
        this.venta = v;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getFactorDesc() {
        return factorDesc;
    }

    public void setFactorDesc(double factorDesc) {
        this.factorDesc = factorDesc;
    }


}

package ejemplos.mvcconpresistencia.ventas.controlador;

import ejemplos.mvcconpresistencia.ventas.excepciones.VentasException;
import ejemplos.mvcconpresistencia.ventas.modelo.Producto;

import java.util.*;

public class Controlador {
    //Singleton
    private static Controlador instance = new Controlador();

    private Controlador() {
        productos = new ArrayList<>();
    }

    public static Controlador getInstance() {
        return instance;
    }

    //--------------
    private List<Producto> productos;

    public String[][] productos() {
        return productos.toArray(new String[0][]);
    }

    public String[][] productosBajoStock() {
        return productos.stream()
                .filter(p -> p.getStock() < p.getStockMin())
                .map(p -> new String[]{
                        p.getSku(),
                        p.getNombre(),
                        "" + p.getPrecio(),
                        "" + p.getStock(),
                        "" + p.getStockMin()
                })
                .toArray(String[][]::new);
    }

    public boolean aumentarStock(String sku, long aumento) {
        Optional<Producto> opProd = findProduct(sku);
        if (opProd.isPresent()) {
            Producto p = opProd.get();
            //se aumenta el stock
            p.setStock(p.getStock() + aumento);
            return true;
        } else {
            return false;
        }
    }

    public boolean disminuirStock(String sku, long dism) throws VentasException {
        Optional<Producto> opProd = findProduct(sku);
        if (opProd.isPresent()) {
            Producto p = opProd.get();
            //se aumenta el stock
            if (p.getStock() <= dism) {
                throw new VentasException("No hay stock suficiente");
            }
            p.setStock(p.getStock() - dism);
            return true;
        } else {
            return false;
        }

    }

    public boolean creaProducto(String sku,
                                String nombre,
                                long precio,
                                long stock,
                                long stockMin) throws VentasException {

        Optional<Producto> prod = findProduct(sku);
        if (prod.isPresent()) {
            return false;
        }
        Producto p = new Producto(sku, nombre, precio, stock, stockMin);
        return productos.add(p);
    }

    public boolean eliminarProducto(String sku) {
        Optional<Producto> prod = findProduct(sku);
        if (prod.isPresent()) {
            return productos.remove(prod);
        } else {
            return false;
        }
    }

    private Optional<Producto> findProduct(String sku) {
        return productos.stream()
                .filter(p -> p.getSku().equals(sku))
                .findFirst();
    }

}

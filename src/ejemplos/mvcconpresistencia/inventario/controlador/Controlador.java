package ejemplos.mvcconpresistencia.inventario.controlador;

import ejemplos.mvcconpresistencia.inventario.excepciones.InventarioException;
import ejemplos.mvcconpresistencia.inventario.modelo.Producto;
import ejemplos.mvcconpresistencia.inventario.persistencia.IOProducto;

import java.util.*;

public class Controlador {
    //Singleton
    private static Controlador instance = new Controlador();

    private Controlador() {
        fileName = "src/ejemplos/mvcconpresistencia/inventario/persistencia/inventario.data";
        productos = new ArrayList<>();
    }

    public static Controlador getInstance() {
        return instance;
    }

    //--------------
    private List<Producto> productos;
    private String fileName;

    public String[][] productos() {
        return productos.stream()
                .map(p -> new String[]{
                        p.getSku(),
                        p.getNombre(),
                        String.format("%,d",p.getPrecio()),
                        String.format("%,d",p.getStock()),
                        String.format("%,d",p.getStockMin())
                })
                .toArray(String[][]::new);
    }

    public String[][] productosBajoStock() {
        return productos.stream()
                .filter(p -> p.getStock() < p.getStockMin())
                .map(p -> new String[]{
                        p.getSku(),
                        p.getNombre(),
                        String.format("%,d",p.getPrecio()),
                        String.format("%,d",p.getStock()),
                        String.format("%,d",p.getStockMin())
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

    public boolean disminuirStock(String sku, long dism) throws InventarioException {
        Optional<Producto> opProd = findProduct(sku);
        if (opProd.isPresent()) {
            Producto p = opProd.get();
            //se aumenta el stock
            if (p.getStock() <= dism) {
                throw new InventarioException("No hay stock suficiente");
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
                                long stockMin) throws InventarioException {

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

    public void exportar(String filename) throws InventarioException {
        IOProducto.getInstance().exportar(productos, filename);
    }

    public void importar(String filename) throws InventarioException {
        productos = IOProducto.getInstance().importar(filename);
    }

    public void cargarDatos() throws InventarioException {
        productos = IOProducto.getInstance().load(fileName);
    }

    public void guardarDatos() throws InventarioException {
        IOProducto.getInstance().save(productos, fileName);
    }
}

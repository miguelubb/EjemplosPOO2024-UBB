package ejemplos.composicion.venta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Fachada {
    private List<Cliente> clientes;
    private List<Producto> productos;
    private List<Venta> ventas;


    public Fachada() {
        clientes = new ArrayList<>();
        productos = new ArrayList<>();
        ventas = new ArrayList<>();

    }

    public boolean agregaCliente(int rut, char dv, String nombre) {
        Cliente c = new Cliente(rut, dv, nombre);
        if (clientes.contains(c)) {
            return false;
        }
        return clientes.add(c);
    }

    public boolean agregaProducto(int id, String nombre, String descrip,
                                  double cant, double precio) {
        Producto p = new Producto(id, nombre, descrip, precio, 0, cant);
        if (productos.contains(p)) {
            return false;
        }
        return productos.add(p);
    }

    public String[] buscaProducto(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                String[] out = new String[5];
                out[0] = producto.getNombre();
                out[1] = producto.getDescripcion();
                out[2] = String.valueOf(producto.getStockActual());
                out[3] = "" + producto.getPrecioActual();
                out[4] = "" + producto.getDescuentoActual();
                return out;
            }
        }
        return null;
    }

    public String[] buscaCliente(int rut) {
        for (Cliente cliente : clientes) {
            if (cliente.getRut() == rut) {
                String[] out = new String[2];
                out[0] = "" + cliente.getDv();
                out[1] = cliente.getNombre();
                return out;
            }
        }
        return null;
    }

    public String[] buscaVenta(int id) {
        DateTimeFormatter fDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fTime = DateTimeFormatter.ofPattern("HH:mm");
        for (Venta venta : ventas) {
            if (venta.getId() == id) {
                String[] out = new String[6];
                out[0] = venta.getFecha().format(fDate);
                out[1] = venta.getHora().format(fTime);
                out[2] = venta.getVendedor();
                out[3] = venta.getCliente().getNombre();
                out[4] = String.valueOf(venta.numDetalles());
                out[5] = String.format("%,.0f.-",venta.total());
                return out;
            }
        }
        return null;
    }

    public boolean creaVenta(int id, int rutCli, String vendedor) {
        Cliente c = findCliente(rutCli);
        if (c == null) {
            return false;
        }
        Venta v = new Venta(id, LocalDate.now(), LocalTime.now(), vendedor, c);
        if (ventas.contains(v)) {
            return false;
        }
        return ventas.add(v);
    }

    private Cliente findCliente(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getRut() == id) {
                return cliente;
            }
        }
        return null;
    }

    public boolean agregProductoAVenta(int idVenta, int idProducto, double cantidad) {
        if (cantidad <= 0) {
            return false;
        }
        Venta v = findVenta(idVenta);
        if (v == null) {
            return false;
        }
        Producto p = findProducto(idProducto);
        if (p == null) {
            return false;
        }
        v.addProducto(p, cantidad);
        return true;
    }

    private Venta findVenta(int idVenta) {
        for (Venta venta : ventas) {
            if (venta.getId() == idVenta) {
                return venta;
            }
        }
        return null;
    }

    private Producto findProducto(int idProducto) {
        for (Producto producto : productos) {
            if (producto.getId() == idProducto) {
                return producto;
            }
        }
        return null;
    }


}

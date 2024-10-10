package ejemplos.singleton.enumerador;

public class Producto {
    //debe ser estatico para que se comparta el nombre de la serie entre las instancias de la clase
    private static final int SERIE_PRODUCTO=Enumerador.getInstance().addSerie();
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    public Producto(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        id=Enumerador.getInstance().nextValue(SERIE_PRODUCTO);
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getId() {
        return id;
    }
}

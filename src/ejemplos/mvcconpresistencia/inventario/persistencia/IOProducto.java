package ejemplos.mvcconpresistencia.inventario.persistencia;

import ejemplos.mvcconpresistencia.inventario.excepciones.InventarioException;
import ejemplos.mvcconpresistencia.inventario.modelo.Producto;

import java.io.*;
import java.util.*;

public class IOProducto {
    private static IOProducto instance=new IOProducto();
    public static IOProducto getInstance() {
        return instance;
    }
    private IOProducto() {

    }


    //archivos de texto
    public List<Producto> importar(String filename) throws InventarioException {
        try {
            List<Producto> out=new ArrayList<>();
            Scanner input=new Scanner(new File(filename));
            input.useLocale(Locale.ENGLISH); //decimal .
            //input.useLocale(Locale.FRENCH); //decimal ,
            input.useDelimiter("[,\t\r\n]+");
            while(input.hasNext()) {
                String sku=input.next();
                String nombre=input.next();
                long precio=input.nextLong();
                long stock=input.nextLong();
                long stockMin=input.nextLong();
                out.add(new Producto(sku,nombre,precio,stock,stockMin));
            }
            input.close();
            return out;
        } catch (FileNotFoundException e) {
            throw new InventarioException("Archivo de importacion: '"+ filename +"', no existe");
        }
    }

    public void exportar(List<Producto> productos, String fileName) throws InventarioException {
        try {
            File file=new File(fileName);
            FileOutputStream fos=new FileOutputStream(file,false);//false significa que lo escribe siempre
            PrintStream out=new PrintStream(fos);
            productos.forEach(p->out.printf("%s, %s, %s, %s, %s%n",p.getSku(),p.getNombre(), p.getPrecio(), p.getStock(), p.getStockMin()));
            out.close();
        } catch (FileNotFoundException e) {
            throw new InventarioException("Archivo de exportación: '"+ fileName +"', no se puede crear en la ruta especificada");
        }
    }

    public void save(List<Producto> productos, String fileName) throws InventarioException {
        File archivo=new File(fileName);
        try {
            ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(archivo));
            out.writeObject(productos);
            out.close();
        } catch (IOException e) {
            throw new InventarioException("Problemas al intentar acceder al archivo de datos: '"+ fileName +"'.");
        }

    }
    public List<Producto> load(String fileName) throws InventarioException {
        File file=new File(fileName);
        List<Producto> productos;
        try {
            ObjectInputStream input=new ObjectInputStream(new FileInputStream(file));
            productos=(List<Producto>) input.readObject();
            input.close();
            return productos;
        } catch (IOException e) {
            throw new InventarioException("Archivo de datos del sistema: '"+ fileName +"', no existe");
        } catch (ClassNotFoundException e) {
            throw new InventarioException("Archivo de datos está corrupto o no guarda datos de la aplicación.");
        }

    }

}

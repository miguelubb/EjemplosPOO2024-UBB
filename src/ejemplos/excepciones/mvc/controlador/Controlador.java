package ejemplos.excepciones.mvc.controlador;

import ejemplos.excepciones.mvc.exception.ContactoRuntimeException;
import ejemplos.mvc.contacto.modelo.Contacto;

import java.util.ArrayList;

public class Controlador {
    private static final Controlador instance=new Controlador();
    private Controlador() {
        misContactos=new ArrayList<>();
    }

    public static Controlador getInstance(){
        return instance;
    }

    private final ArrayList<Contacto> misContactos;

    public boolean CrearContacto(String nombre, String telefono){
        return misContactos.add(new Contacto(nombre, telefono));
    }

    public String[][] buscarPorNombre(String nombre){
        ArrayList<Contacto> output = getContactos(nombre);
        String[][] out=new String[output.size()][2];
        for (int i = 0; i < out.length; i++) {
            out[i][0]=output.get(i).getNombre();
            out[i][1]=output.get(i).getTelefono();
        }
        return out;
    }

    private ArrayList<Contacto> getContactos(String nombre) {
        ArrayList<Contacto> output=new ArrayList<>();
        for (Contacto contacto : misContactos) {
            if(contacto.getNombre().contains(nombre)){
                output.add(contacto);
            }
        }
        return output;
    }

    public String buscarPorTelefono(String telefono){
        for (Contacto contacto : misContactos) {
            if(contacto.getTelefono().equals(telefono)){
                return contacto.getNombre();
            }

        }
        return "<no encontrado>";
    }

    public boolean actualizarTelefono(String nombre, String fonoActual, String fonoNuevo){
        Contacto actual=new Contacto(nombre, fonoActual);
        if(misContactos.remove(actual)){
            Contacto nuevo=new Contacto(nombre, fonoNuevo);
            return misContactos.add(nuevo);
        }
        return false;
    }

    public boolean actualizarNombre(String nombreActual, String nombreNuevo){
        boolean output=false;
        for (Contacto contacto : misContactos) {
            if(contacto.getNombre().equals(nombreActual)){
                contacto.setNombre(nombreNuevo);
                output=true;
            }
        }
        return output;
    }

    public boolean elimnarTelefono(String telefono) throws ContactoRuntimeException{
        //un teléfono pertenece a un único contacto.
        for (Contacto contacto : misContactos) {
            if(contacto.getTelefono().equals(telefono)){
                return misContactos.remove(contacto);
            }
        }
        throw new ContactoRuntimeException("No se puede eliminar un contacto inexistente");
    }

    public boolean eliminarContacto(String nombre){
        //se eliminan todos los teléfonos de un contacto
        ArrayList<Contacto> contactosPorEliminar=getContactos(nombre);
        return misContactos.removeAll(contactosPorEliminar);
    }
}

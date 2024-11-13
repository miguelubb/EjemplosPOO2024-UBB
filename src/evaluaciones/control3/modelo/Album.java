package evaluaciones.control3.modelo;

import evaluaciones.control3.excepciones.SelloDiscoException;

import java.time.LocalDate;
import java.util.*;

public class Album {
    private String nombre;
    private LocalDate fechaCreacion;
    private List<Cancion> canciones;

    public Album(String nombre, LocalDate fechaCreacion) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album album)) return false;
        return Objects.equals(nombre, album.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        return nombre + ";"+ fechaCreacion;
    }

    public float getDuracion(){
        float out=0;
        for (Cancion cancion : canciones) {
            out+=cancion.getDuracion();
        }
        return out;
    }

    public void addCancion(Cancion cancion) throws SelloDiscoException {
        if(!canciones.contains(cancion)){
            canciones.add(cancion);
        }else{
            throw new SelloDiscoException("El cancion ya existe");
        }
    }
    public void removeCancion(Cancion cancion) throws SelloDiscoException {
        if(canciones.contains(cancion)){
            canciones.remove(cancion);
        }else{
            throw new SelloDiscoException("La canción no forma parte del album");
        }
    }

    public boolean isCancion(Cancion cancion){
        return canciones.contains(cancion);
    }
    public Cancion[] getCanciones(){
        return canciones.toArray(new Cancion[canciones.size()]);
    }

}

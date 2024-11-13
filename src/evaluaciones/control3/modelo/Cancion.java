package evaluaciones.control3.modelo;

import evaluaciones.control3.excepciones.SelloDiscoException;

import java.util.*;

public class Cancion {
    private String nombre;
    private float duracion;
    private Genero genero;
    private String nombreInterprete;
    private List<Album> albunes;
    public Cancion(String nombre, float duracion, Genero genero, String nombreInterprete) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.genero = genero;
        this.nombreInterprete = nombreInterprete;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getNombreInterprete() {
        return nombreInterprete;
    }

    public void setNombreInterprete(String nombreInterprete) {
        this.nombreInterprete = nombreInterprete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cancion cancion)) return false;
        return Objects.equals(nombre, cancion.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(nombre);
        sb.append(";").append(duracion);
        sb.append(";").append(genero);
        sb.append(";").append(nombreInterprete);
        return sb.toString();
    }

    public void addAlbum(Album album) throws SelloDiscoException {
        if(!albunes.contains(album)){
            albunes.add(album);
        }else{
            throw new SelloDiscoException("Album ya existe, no se puede agregar otra vez");
        }
    }
    public void removeAlbum(Album album) throws SelloDiscoException {
        if(albunes.contains(album)){
            albunes.remove(album);
        }else{
            throw new SelloDiscoException("La canción no pertenece a dicho album, no se puede remover");
        }
        albunes.remove(album);
    }
    public Album[] getAlbums() {
        return albunes.toArray(new Album[albunes.size()]);
    }
}

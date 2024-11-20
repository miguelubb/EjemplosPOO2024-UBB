package evaluaciones.control4.modelo;

import java.time.LocalDate;
import java.util.*;

public class Autor {
    private int id;
    private String nombre;
    private String pais;
    private LocalDate fechaNacimiento;
    private List<String> premios;
    private List<Pintura> pinturas;

    public Autor(){
        premios=new ArrayList<>();
        pinturas=new ArrayList<>();
    }
    //fecha debe venir en formato ISO, es decir "yyyy-MM-dd', ejemeplo "1908-11-23"
    public Autor(int id, String nombre, String pais, String fechaNacimiento,  List<String> premios) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
        this.premios = premios;
        this.pinturas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
    }

    public List<String> getPremios() {
        return premios;
    }

    public void setPremios(List<String> premios) {
        this.premios = premios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor autor)) return false;
        return id == autor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int getCantPremios() {
        return getPremios().size();
    }

    public int getCantPinturas() {
        return pinturas.size();
    }

    public void addPintura(Pintura pintura) {
        pinturas.add(pintura);
    }
}
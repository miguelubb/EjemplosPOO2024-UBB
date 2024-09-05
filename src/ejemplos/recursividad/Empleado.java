package ejemplos.recursividad;
import java.util.*;
public class Empleado {
    private String rut;
    private String nombre;
    private String cargo;
    private boolean estaVigente;
    //impelementación de asociación recursiva de supervisor
    private Empleado supervisor;
    private List<Empleado> supervisados;

    public Empleado(String rut, String nombre, String cargo) {
        this.rut = rut;
        this.nombre = nombre;
        this.cargo = cargo;
        estaVigente = true;
        supervisor = null;
        supervisados = new ArrayList<Empleado>();
    }

    public Empleado getSupervisor() {
        return supervisor;
    }

    public boolean isEstaVigente() {
        return estaVigente;
    }

    //para mantener la consistencia y eviar las referencias circulares
    //se actúa directamente
    // sobre los atributos y no a través de los métodos
    //addSupervisado, removeSupervisado
    public void setSupervisor(Empleado supervisor) {
        //si ya estaba supervisado por alguien
        if(this.supervisor!=null){
            //Para mantener la integridad referencial
            //se elimina del supervisor actual.
            this.supervisor.supervisados.remove(this);
        }
        this.supervisor = supervisor;
        if(supervisor!=null){
            //para mantener la integridad referencial, se agrega al nuevo supervisor
            supervisor.supervisados.add(this);
        }
    }

    public void addSupervisado(Empleado empleado) {
        empleado.setSupervisor(this);
    }
    public boolean removeSupervisado(Empleado empleado) {
        if(supervisados.contains(empleado)){
            empleado.setSupervisor(null);
            return true;
        }
        return false;
    }

    public void darDeBaja(){
        estaVigente=false;
        for (Empleado empleado : supervisados) {
            empleado.setSupervisor(null);
        }
    }

    public void darDeBajaYReemplazar(Empleado nuevoSupervisor){
        estaVigente=false;
        for (Empleado empleado : supervisados) {
            empleado.setSupervisor(nuevoSupervisor);
        }
    }

    //listados
    public String dependientes(){
        StringBuffer sb = new StringBuffer();
        sb.append(this.nombre+"[");
        for (Empleado supervisado : supervisados) {
            sb.append(supervisado.dependientes());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

    public String dependientesConNiveles(){
        ArrayList<String> dep=new ArrayList<>();
        this.dependientesConNiveles("1.","",dep);
        StringBuffer sb = new StringBuffer();
        for (String s : dep) {
            sb.append(s+"\n");
        }
        return sb.toString();
    }

    private void dependientesConNiveles(String nivel, String tab, ArrayList<String> dep){
        dep.add(tab+nivel+" "+nombre+ "("+cargo+")");
        int i=1;
        for (Empleado supervisado : supervisados) {
            supervisado.dependientesConNiveles(nivel+i+".", tab+"\t",dep);
            i++;
        }
    }


}

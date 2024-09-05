package ejemplos.composicion;

import java.util.ArrayList;
import java.util.List;

public class testRol {
    public static void main(String[] args) {

        List<Privilegio> privilegios=new ArrayList<>();
        List<Rol> roles=new ArrayList<>();
        String[] privilegiosCRUD={
                "CREATE", "READ", "UPDATE", "DELETE"
        };
        String[] entidades={
                "USER","CLIENTES", "PROVEEDORES","FACTURA","DETALLE_FACTURA","PRODUCTO"
        };
        Privilegio p;
        Rol rol;
        for(int i=0;i< entidades.length;i++){
            rol=new Rol(i+1, entidades[i]);
            roles.add(rol);
            for (int j = 0; j < privilegiosCRUD.length; j++) {
                p=new Privilegio(j+1,entidades[i]+"/"+privilegiosCRUD[j]);
                privilegios.add(new Privilegio(j+1,privilegiosCRUD[j]));
                rol.agregaPrivilegio(p);
            }
        }
        rol=new Rol(0,"ADMIN");
        for (Privilegio privilegio : privilegios) {
            rol.agregaPrivilegio(privilegio);
        }
        roles.add(rol);
        for (Rol r : roles) {
            System.out.println(r);
        }
    }
}

# Modelo conceptual
Este ejemplo es la implementación de la composición entre la clase Rol y Privilegio, los cuales 
forman parde de un módulo mayor que incluye a los usuarios del sistema. 
Un objeto Rol representa el rol que un usuario de un sistema tiene. Los privilegios son las
autorizaciones que tiene ese rol dentro del sistema. Por ejemplo un usuario con el rol de cliente
podrá ver el catálog, comparar productos, etc.  Pero no podrá crear nuevos productos en el sistema
o eliminarlos.  

## Diagrama de clases
Por claridad, en el siguiente diagrama se han omitido los método setter y getter, además de los constructors. Sin embargo
hay que considerarlos cuando se implemente el diagrama.

````mermaid
classDiagram
    
    class Rol{
        -id:int
        -Nombre: string
        
        +tienePrivilegio(p:Privilegio) boolean
        +toString() String
        +agregaPrivilegio(p:Privilegio) boolean
        +eliminaPrivilegio(p:Privilegio) boolean
        +getPrivilegios() List<Privilegio>
    }
    
    class Privilegio{
        -id:int
        -nombre:String
        +toString() String
        +getRoles() List<Rol>
    }
    Rol "*" o-- "*" Privilegio

````

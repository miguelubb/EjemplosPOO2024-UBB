# Modelo conceptual
Este ejemplo es la implementación de la composición entre la clase Venta y DetalleVenta, junto con la asociación con las clases
cliente y producto.



## Diagrama de clases
Por claridad, en el siguiente diagrama se han omitido los método setter y getter, además de los constructors. Sin embargo
hay que considerarlos cuando se implemente el diagrama.

````mermaid
---
title: Módulo de ventas
---
classDiagram
    class Venta{
        -id: int
        -fecha: LocalDate
        -hora: LocalTime
        -factorDescuento: double
        -vendedor: String
        +getTotal() double
        +toString() String
        +agregaProducto(p:Producto, cantidad: double):boolean
        +getDetalle():List<DetalleVenta>
       }
    
    class DetalleVenta{
        -precioVenta:double
        -cantidad:double
        -factorDescVta: double
    }
    
    class Producto{
        -id: int
        -nombre: String
        -descripcion: String
        -stockActual: double
        -precioActual: double
        -descuentoActual: 
        
    }
    class Cliente{
        -rut: int
        -dv: char
        -nombre: String
    }
   
    direction LR
    Venta *-- "*" DetalleVenta
    Cliente  -- "*" Venta: paga
    DetalleVenta "*" -- Producto:incluye
````
### Separación de aspectos
Un principio importante en el diseño OO es la separación de aspectos. Esto consiste en aislar cierto aspecto o funcionalidad
del códiog de modo que sea un módulo independiente del resto. Para lograr esto se crean interfaces o clases que hacen las veces de "Fachada".
Una fachada será el punto de acceso al módulo ocultando el interior a quien utiliza dicho módulo. Esto simplifica la interacción y baja el acoplamiento del sistema.

Un ejemplo de esto lo tenemos con la clase que usa el módulo de ventas:

````mermaid
---
title: diseño con alto acoplamiento
---
classDiagram
    direction LR
    Venta *-- "*" DetalleVenta
    Cliente  -- "*" Venta: paga
    DetalleVenta "*" -- Producto:incluye
    VentaTextUI --> "*" Venta
    VentaTextUI --> "*" Producto
    VentaTextUI --> "*" Cliente
````

El problema aquí es que al cambiar la interfas de texto por una interfaz gráfica (web, móvil, escritorio, etc.) sea necesario
volver a programar toda la lógica del negocio asociada con la venta propíamente tal, la cual es parte del módulo y no de la
interfaz de usuario.

La solución es crear una clase "Fachada" que sea el punto de comunicación entre las clases del interior del módulo con las del exterior:
````mermaid
---
title: diseño con clase fachada
---
classDiagram
    class Fachada{
        +creaVenta(rutCli: int, vendedor: String, desc: double) int
        +agregProductoAVenta(idVenta: int, idProducto: int, cantidad: double): boolean
        +agregaCliente(rut: int, dv:char, nombre:String) boolean
        +agregaProducto(id, nombre, descrip, cant, precio) boolean
        +buscaProducto(id:int) String[]
        +buscaCliente(rut:int) String[]
        +buscaVenta(id: int) String[]
    }
    class VentaTextUI{
        menuPrincipal()
        venta()
    }
    direction LR
    Venta *-- "*" DetalleVenta
    Cliente  -- "*" Venta: paga
    DetalleVenta "*" -- Producto:incluye
    Fachada -->"*" Venta
    Fachada -->"*" Producto
    Fachada -->"*" Cliente
    VentaTextUI --> Fachada
````
La clase fachada baja el acoplamiento global, porque interactúa con el exterior ocultando las clases que hay en el interior, de modo que, toda clase que necesite crear una venta, lo haga sin crear objetos del módulo de venta.
Si en el futuro es necesario cambiar una clase, eliminarla o crear nuevas para gestionar mejor las ventas, esto no afecta a quienes usan el módulo, dado que no interactúan directamente con ellas.
A esto también se le denomina **"Indirección"**

## Diagrama de secuencia
El siguiente diagrama muestra la secuencia de llamadas de métodos para realizar la venta:
````mermaid
zenuml
    title venta()
    VentanaTextUI.venta() {
      Fachada.buscaCliente(rut) {
        ListaCliente.get()
        return 
      }
      Fachada.CreaVenta(rut, vendedor, desc) {
            new Venta
            return id
      }
      while( i < n){
          Fachada.buscaProducto(idProd) {
            ListaProducto.get()
            return 
          }
          Fachada.agregaProductoAVenta(id, idProd, cant) {
                Venta.agregaProducto(p, cantidad){
                    new DetalleVenta
                    return true/false
                }
                return true/false
          }
      }
      Fachada.buscaVenta(id) {
           ListaVenta.get()
           return
    }
````
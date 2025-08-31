# Ejemplo diagrama de secuencia
## juego de damas cliente/servidor

````mermaid
sequenceDiagram
    participant Jugador A
    participant Servidor
    participant Jugador B
    
    Jugador A->>Servidor: Conectar a la partida (TCP/IP)
    Servidor->>Jugador B: Notificar conexión de Jugador A
    Jugador B->>Servidor: Conectar a la partida (TCP/IP)
    Servidor->>Jugador A: Notificar conexión de Jugador B
    
    Jugador A->>Servidor: Realizar movimiento (pieza A1 a B2)
    Servidor->>Jugador B: Actualizar tablero (pieza A1 a B2)
    
    Jugador B->>Servidor: Realizar movimiento (pieza C3 a D4)
    Servidor->>Jugador A: Actualizar tablero (pieza C3 a D4)
    
    Jugador A->>Servidor: Realizar movimiento (pieza B2 a C3)
    Servidor->>Jugador B: Actualizar tablero (pieza B2 a C3)
    
    Jugador B->>Servidor: Realizar movimiento (pieza D4 a E5)
    Servidor->>Jugador A: Actualizar tablero (pieza D4 a E5)
    
    Jugador A->>Servidor: Fin de la partida (Ganador Jugador A)
    Servidor->>Jugador B: Fin de la partida (Ganador Jugador A)
````
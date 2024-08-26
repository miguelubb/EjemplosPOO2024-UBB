# Recursividad en Programación
Dr. Miguel Romero
## ¿Qué es la Recursividad?

La recursividad es una técnica de programación donde **una función se llama a sí misma para resolver un problema algorítmico**.
Esta técnica es especialmente útil cuando un problema puede dividirse en subproblemas más pequeños de la misma naturaleza. En otras palabras, en lugar de resolver el problema completo de una vez, la recursividad permite descomponer el problema en partes más manejables y resolverlo paso a paso.

## Forma General de un Algoritmo Recursivo

Un algoritmo recursivo generalmente tiene dos componentes clave:

1. **Caso Base:** Es la condición de término de la recursión. Es el punto en el cual el problema es tan simple que puede resolverse directamente sin necesidad de más llamadas recursivas. Este caso evita que la recursión continúe indefinidamente, lo que llevaría a un desbordamiento de la pila de llamadas (_stack overflow_).

2. **Caso General:** Es la parte del algoritmo que divide el problema en uno o más subproblemas más pequeños, y luego se llama recursivamente a sí mismo para resolver esos subproblemas. Es crucial que cada llamada recursiva se acerque al caso base para que eventualmente la recursión termine.

## Ejemplo de Recursividad

Consideremos el cálculo del factorial de un número $n$, denotado como $n!$, que es el producto de todos los números enteros positivos desde $1$ hasta $n$.
La definición recursiva del factorial, expresado en lenguaje matemático sería:
$$
n! =
\begin{cases}
1 & \text{si } n = 1 \\
n \cdot(n-1)!  & \text{si } n > 1 \\
\end{cases}
$$
Los elementos del algoritmo a implementar serían:
- **Caso Base:** El factorial de $1$ es $1$, es decir, $1! = 1$.
- **Caso General:** Para $n > 1$, el factorial de `n` se puede definir como $n! = n \cdot (n-1)!$.

Así, el algoritmo recursivo para calcular el factorial sería:

```java
int factorial(int n) {
    if (n == 1) { // Caso Base
        return 1;
    } else { // Caso General
        return n * factorial(n - 1);
    }
}
```
### Importancia de Acercarse al Caso Base

En cada paso de la recursión, es vital que el algoritmo progrese hacia el caso base. En el ejemplo del factorial, cada llamada recursiva reduce el valor de `n` hasta que eventualmente llega a `1`, momento en el cual la recursión termina. Si un algoritmo recursivo no se acerca al caso base, la recursión se convertirá en una **recursión infinita**, provocando un error en el programa.

### Ejemplo de ejecución: ``factorial(5)``
# Evolución del Stack de Llamadas para Factorial de 5

## Evolución del Stack de Llamadas

Cuando llamamos a `factorial(5)`, el stack de llamadas evoluciona de la siguiente manera:

### Representación Visual del Stack de Llamadas

A medida que se realizan las llamadas recursivas, el stack se ve así:

```plaintext
llamada inicial         caso general         caso general          caso general           caso base
  5! = 5 * 4!           4! = 4 * 3!           3! = 3 * 2!           2! = 2 * 1!             1! = 1 
|factorial(5)| 5* --> |factorial(4)| 4* --> |factorial(3)| 3* --> |factorial(2)| 2* --> |factorial(1)|
--------------        |factorial(5)|        |factorial(4)|        |factorial(3)|        |factorial(2)|    
                      --------------        |factorial(5)|        |factorial(4)|        |factorial(3)|
                                             -------------        |factorial(5)|        |factorial(4)|
                                                                  --------------        |factorial(4)|
                                                                                        --------------
````
Luego, el stack se empieza a desmontar cuando se alcanza el caso base `factorial(1)`.
```plain
llamada inicial         caso general         caso general          caso general           caso base
  5! = 5 * 24           4! = 4 * 6            3! = 3 * 2            2! = 2 * 1              1! = 1 
|    120     | <-- 5* |     24     | <-- 4* |     6      | <-- 3* |     2      | <-- 2* |    1       |
--------------        |factorial(5)|        |factorial(4)|        |factorial(3)|        |factorial(2)|    
                      --------------        |factorial(5)|        |factorial(4)|        |factorial(3)|
                                             -------------        |factorial(5)|        |factorial(4)|
                                                                  --------------        |factorial(4)|
                                                                                        --------------
```

## Eficiencia de un algoritmo recursivo
Un algoritmo recursivo, en si mismo, no es más eficiente que uno iterativo. En efectcto, puede ser más lento por la necesidad
de requerir utilizar el stack de llamadas de forma intensiva, incluso podría llenarse probocando un _stack overflow_.

Independientemente de la necesidad del uso del Stack, un algoritmo recursivo bien diseñado puede ser muy eficiente. Por lo tanto,
la eficiencia del algoritmo dependerá del diseño, y no de la técnica de recursividad.

Un ejemplo clásico de algoritmo recursivo que es mucho más lento que su versión iterativa es la del cálculo del i-ésimo término
de la serie de fibonacci.  El algoritmo recursivo en java es el siguiente:
```java
 public static int fib(int n) {
        if (n == 0){
            return 0;
        }else if (n == 1){
            return 1;
        }else{
            return fib(n-1) + fib(n-2);
        }
    }
```
Aquí se pueden identificar dos casos base y un caso general:
1. Casos base:
    * `si n=0, retornar 0`
    * `si n=1, retornar 1`
2. Caso general: 
    * `retornar fib(n-1)+fib(n-2)`

### Análisis de la eficiencia de la Versión Recursiva

- **Complejidad Temporal:** La complejidad temporal de esta implementación recursiva es $O(2^n)$. Esto se debe a que cada llamada a `fibonacciRecursive` genera dos llamadas adicionales, lo que produce una explosión exponencial de llamadas recursivas.
- **Problema de Superposición:** La implementación recursiva recalcula los mismos valores múltiples veces. Por ejemplo, para calcular $F(5)$, se calculará $F(3)$ y $F(2)$ varias veces, lo que es ineficiente.
### Versión iterativa
El siguiente código presenta la versión iterativa.
```` java
int fibonacciIterative(int n) {
    if (n == 0) {
        return 0;
    } else if (n == 1) {
        return 1;
    }
    
    int a = 0, b = 1, c = 0;
    for (int i = 2; i <= n; i++) {
        c = a + b; // Calcular el siguiente término
        a = b;     // Actualizar a
        b = c;     // Actualizar b
    }
    return c;
}
````
Esta versión no tiene el problema de la superposción de llamadas, es decir, no calcula reiteradamente algún término de fibonacci.
La complejidad temporal de esta implementación recursiva es $O(n)$, este agloritmo solo necesita $n-1$ iteraciones para calcular el _n-esimo_ término de fibonacci.

## Ejercicios
1. Defina una función recursiva para encontrar el cuociente de la división entera. Para este ejercicio, utilice la noción de la división como una resta sucesiva entre el dividendo y el divisor.¿cuál es el caso base y cuál es el general?
2. Defina una funcion potencia de manera recursiva. Implemente en función de multiplicaciones sucesivas de la base.Caso base exp=0 y exp=1.

### Solucion problema división entera
se define la fución recursiva de $a/b$:
$$
a/b =
\begin{cases}
0 & \text{si } a < b \\
1 + (a-b)/b   & \text{si } a > b \\
\end{cases}
$$

Implementación en java:
````java
//pre: divisor es distinto de 0
    public static int division(int dividendo, int divisor){
        if(dividendo < divisor){
            return 0;
        }else{
            return 1+division(dividendo-divisor,divisor);
        }
    }
````

### Solución problema de potencia
se define la fución recursiva de $a^b$:
$$
a^b =
\begin{cases}
1 & \text{si } b = 0 \\
a & \text{si } b = 1 \\
a \cdot a^{b-1}  & \text{si } b > 1 \\
\end{cases}
$$

Implementación en java
````java
 public static double potencia(int base, int exp){
        if(exp==0){
            return 1;
        }else if(exp==1){
            return base;
        }else{
            return base*potencia(base,exp-1);
        }
    }
````

## Dividir para reinar una estrategia para diseñar algoritmos recursivos eficientes
Una estrategia de diseño de algoritmos recursivos consiste en dividir el problema en un 
factor en vez de restar. En general, dicha estrategia lleva a soluciones eficientes, 
en espcial cuando es posible descartar una parte de los subproblemas.

Mientras más subproblemas se descarten, mayor será el ahorro de tiempo.

### Revisitando el problema de la división
En la implementación anterior, en cada llamada el problema se reduce en una unidad en el exponente. Así podemos ver que 
la cantidad de mutliplicaciones son $b-1$. Sin embargo, se puede hacer mejor.

Mejoraremos el caso general, teniendo encuenta si el exponente es par o impar. 

En el caso de que el exponente sea par, se calculará: $a^b=a^{(b-1)/2} * a^{(b-1)/2}$. Por ejemplo: $2^8= 2^4 * 2^4$.
En el caso de que el exponente sea impar, se calculará: $a^b=a * a^{(b-1)/2} * a^{(b-1)/2}$. Por ejemplo: $2^9=2 * 2^4 * 2^4$.

$$
a^b =
\begin{cases}
1 & \text{si } b = 0 \\
a & \text{si } b = 1 \\
a^{\frac{b}{2}} \cdot a^{\frac{b}{2}}  & \text{si } b \text{ es par} \\
a \cdot a^{\frac{b-1}{2}} \cdot a^{\frac{b-1}{2}}  & \text{si } b \text{ es par}
\end{cases}
$$

En el caso general, como se puede observar, se ha dividido el problema en **2 subproblemas iguales**, por lo tanto, no es necesario realizar dos veces
el cálculo, bastará con calcularlo una vez y usarlo dos veces. Con lo cual se reduce notablemente la cantidad de multiplicaciones necesarias,
las cuales son del orden de $O(log_2{b})$ multiplicaciones.

El siguiente código java implementa esta idea.

````java
 public static double potenciaRapida(int base, int exp){
    if(exp==0){
        return 1;
    }else if(exp==1){
        return base;
    }else if(exp%2==0){
        //si es par
        double semiPotencia=potenciaRapida(base,exp/2);
        return semiPotencia*semiPotencia;
    }else{
        //si exp es impar
        double semiPotencia=potenciaRapida(base,(exp-1)/2);
        return base*semiPotencia*semiPotencia;
    }
}
````

Para mostrar la diferencia en tiempo de ejecución se realizó el cálculo de $2^{1000}$ en ambas versiones recursivas. Además, se comparó con `Math.pow()`. 
Los resultados ordenados del más rápido al más lento son los siguientes:

| Algoritmo        | tiempo en $\mu$ |
|------------------|----------------:|
| potenciaRapida() |               1 |
| Math.pow()       |              42 |
| potencia()       |             115 |             




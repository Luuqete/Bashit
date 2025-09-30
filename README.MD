# Bashit

*Bahit es una terminal hecha en Java con Swing la cual soporta las operaciones básicas E/S*

## Características

- Entrada de texto segura (`nextLine`, `nextInt`, `nextDouble`, etc.).
- Salida de texto (`print` y `println`) con soporte thread-safe.
- Manejo de radix (base numérica) para números enteros.
- Control de edición para que el usuario no borre texto no permitido.
- Fácil integración en proyectos Java como librería `.jar`.

## Instalación

Descargá el jar [acá](https://github.com/Luuqete/Bashit/releases/download/v1.0.0/bashit.jar) e importalo a tu proyecto Java


## Uso

El paquete principal para importar es bashit

```java
import bashit.Bashit;
import bashit.IOBashit;
```
## Métodos principales



|Método	|Descripción|
|-|-|
print(Object text)	|Muestra texto en la consola sin salto de línea.
println(Object text)|	Muestra texto en la consola con salto de línea.
nextLine()	|Lee una línea completa de entrada del usuario.
nextInt() / nextInt(int radix)	|Lee un número `entero`. Opcionalmente en base radix.
nextDouble()|	Lee un número `double`.
nextFloat()	|Lee un número `float`.
nextLong() / nextLong(int radix)	|Lee un número `long`. Opcionalmente en base radix.
nextShort() / nextShort(int radix)	|Lee un número `short`. Opcionalmente en base radix.
nextByte() / nextByte(int radix)	|Lee un número `byte`. Opcionalmente en base radix.
nextBigDecimal()	|Lee un `BigDecimal`.
nextBigInteger() / nextBigInteger(int radix)|	Lee un `BigInteger`. Opcionalmente en base radix.
useRadix(int radix)	|Cambia la base predeterminada para lectura de números.|
radix() | Devuelve la base numérica usada por defecto


## Código de ejemplo

```java
    public static void main(String[] args){

        IOBashit bash = Bashit.getIOBash();
        bash.println("Hello, World from this bash!");
        bash.println("What is your name?");
        String yourName = bash.nextLine();

        bash.print("Hello, " + yourName + "! How are you?: ");
        int yourAge = bash.nextInt();

        bash.println("Wow! " + yourAge + " years old! Nice! What is your favorite octal number?");

        int yourOctal = bash.nextInt(8);
        bash.println("Cool! Your favorite octal number is " + yourOctal + "!");
    }
```

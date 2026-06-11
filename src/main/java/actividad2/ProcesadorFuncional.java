package actividad2;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Function;

public class ProcesadorFuncional {
 
    // Justificación:
    // Utilizamos Predicate<Producto> en lugar de Filtro porque Predicate es la interfaz funcional 
    // estándar de java.util.function diseñada para evaluar una condición (devuelve boolean).
    // Utilizamos Function<Producto, String> en lugar de Transformador porque Function toma un 
    // argumento de un tipo (Producto) y devuelve un resultado de otro tipo (String).
    public List<String> procesar(List<Producto> productos,
                                  Predicate<Producto> f,
                                  Function<Producto, String> t) {
        List<String> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (f.test(p)) {
                resultado.add(t.apply(p));
            }
        }
        return resultado;
    }
 
    public static void main(String[] args) {
        ProcesadorFuncional proc = new ProcesadorFuncional();
        List<Producto> lista = List.of(
            new Producto("Laptop", 1200),
            new Producto("Mouse", 25),
            new Producto("Monitor", 350)
        );
 
        // Uso con expresiones lambda
        List<String> caros = proc.procesar(lista,
            p -> p.precio() > 100,
            p -> p.nombre().toUpperCase()
        );
        System.out.println("Productos caros en mayúsculas (con lambda): " + caros);
        
        // Uso con referencia a método (Method Reference)
        // Ejemplo adicional: Obtener el nombre tal cual usando una referencia al método nombre() del record
        List<String> nombresDeCaros = proc.procesar(lista,
            p -> p.precio() > 100,
            Producto::nombre // <- Method Reference
        );
        System.out.println("Nombres de productos caros (con Method Reference): " + nombresDeCaros);
    }
}
 
record Producto(String nombre, double precio) {}

package actividad4;

import java.util.List;
import java.util.function.Function;

public class TransformacionCadenas {
    public static void main(String[] args) {
        List<String> nombres = List.of(
            "   jUaN pErEz  ",
            "mArIa gOmEz",
            "  CARLOS lopez "
        );

        // 1. Eliminar espacios al inicio y al final
        Function<String, String> trim = String::trim;

        // 2. Convertir todo a minúsculas
        Function<String, String> toLower = String::toLowerCase;

        // 3. Capitalizar la primera letra de cada nombre
        Function<String, String> capitalize = s -> {
            if (s.isEmpty()) return s;
            String[] parts = s.split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String part : parts) {
                if (!part.isEmpty()) {
                    sb.append(Character.toUpperCase(part.charAt(0)))
                      .append(part.substring(1))
                      .append(" ");
                }
            }
            return sb.toString().trim();
        };

        // 4. Anteponer el prefijo "Sr./Sra. "
        Function<String, String> addPrefix = s -> "Sr./Sra. " + s;

        // --- Demostración 1: andThen() en orden lógico ---
        // Se ejecuta: trim -> toLower -> capitalize -> addPrefix
        Function<String, String> pipeline1 = trim
            .andThen(toLower)
            .andThen(capitalize)
            .andThen(addPrefix);
        
        System.out.println("--- Composición 1: trim -> toLower -> capitalize -> addPrefix ---");
        System.out.println("El resultado es correcto, ya que primero se normaliza el formato y al final se agrega el prefijo.");
        for (String nombre : nombres) {
            System.out.println("Original: '" + nombre + "' -> Transformado: '" + pipeline1.apply(nombre) + "'");
        }

        System.out.println();

        // --- Demostración 2: compose() para invertir el orden o cambiarlo ---
        // compose() ejecuta la función que se pasa como argumento ANTES de la función sobre la que se llama.
        // Vamos a construir un pipeline extraño para demostrar cómo cambia el resultado.
        // Queremos ejecutar: trim -> toLower -> addPrefix -> capitalize
        // Esto se puede lograr con compose si leemos de derecha a izquierda, pero lo haremos mezclando:
        Function<String, String> pipeline2 = capitalize
            .compose(addPrefix)
            .compose(toLower)
            .compose(trim);
            // Orden de ejecución real: trim -> toLower -> addPrefix -> capitalize
        
        System.out.println("--- Composición 2 usando compose() (equivalente a trim -> toLower -> addPrefix -> capitalize) ---");
        System.out.println("El resultado cambia: al anteponer el prefijo 'Sr./Sra.' ANTES de capitalizar,");
        System.out.println("la 'S' de 'Sr./sra.' puede quedar mal procesada o forzada dependiendo de la lógica, y la 'sra' queda en minúscula.");
        for (String nombre : nombres) {
            System.out.println("Original: '" + nombre + "' -> Transformado: '" + pipeline2.apply(nombre) + "'");
        }
    }
}

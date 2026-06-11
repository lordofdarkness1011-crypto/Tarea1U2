package actividad3;

import java.util.List;

@FunctionalInterface
public interface CalculadoraTributo {
    double calcular(double monto, double tasa);
}

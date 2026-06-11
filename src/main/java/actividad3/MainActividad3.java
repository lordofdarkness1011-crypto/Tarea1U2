package actividad3;

import java.util.List;

public class MainActividad3 {
    public static void main(String[] args) {
        // Implementaciones vía lambda
        // 1. IVA del 15% (ignoramos la tasa enviada y multiplicamos por 0.15)
        CalculadoraTributo iva = (monto, tasaIgnorada) -> monto * 0.15;
        
        // 2. Retención en la fuente del 2% (ignoramos la tasa enviada y multiplicamos por 0.02)
        CalculadoraTributo retencion = (monto, tasaIgnorada) -> monto * 0.02;
        
        // 3. Impuesto variable (utiliza la tasa que recibe como parámetro)
        CalculadoraTributo impuestoVariable = (monto, tasa) -> monto * tasa;

        List<Double> facturas = List.of(100.0, 250.50, 50.0, 1000.0, 30.25);

        System.out.println("--- Cálculo de Impuestos ---");
        for (double factura : facturas) {
            System.out.println("Monto base de factura: $" + factura);
            System.out.println("  IVA (15%): $" + iva.calcular(factura, 0.15));
            System.out.println("  Retención (2%): $" + retencion.calcular(factura, 0.02));
            System.out.println("  Variable (10%): $" + impuestoVariable.calcular(factura, 0.10));
            System.out.println("-----------------------------");
        }
    }
}

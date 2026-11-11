package uniquindio.edu.co;

import javax.swing.*;
import java.time.LocalDate;

public class Aplicacion {
    static void main() {
        Restaurante res = new Restaurante("Sabor & tradición", "1", "Armenia, quindío");
        res.agregarServicio(new RapidoExpress());
        res.agregarServicio(new EntregaSegura());
        int opcion;
}
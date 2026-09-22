package edu.uees.refactor.service;

import edu.uees.refactor.domain.Reserva;

/**
 * Coordina el procesamiento de una reserva delegando la validación.
 */
public class ServicioReservas {

    private final ValidadorReserva validador;

    public ServicioReservas() {
        this.validador = new ValidadorReserva();
    }

    public double procesar(
            Reserva r,
            int horasAnticipacion) {

        if (!validador.esValida(r, horasAnticipacion)) {
            return 0;
        }

        double total = r.calcularTotal();

        System.out.println(
                "Guardando reserva " + r.getId()
        );

        System.out.println(
                "Correo enviado a " + r.getCorreo()
        );

        r.confirmar();

        return total;
    }
}

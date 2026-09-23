package edu.uees.refactor.service;

import edu.uees.refactor.domain.Reserva;

/**
 * Coordina el procesamiento de una reserva.
 *
 * Las validaciones y la notificación se delegan a clases con una
 * responsabilidad específica.
 */
public class ServicioReservas {

    private final ValidadorReserva validador;
    private final NotificadorReserva notificador;

    public ServicioReservas() {
        this.validador = new ValidadorReserva();
        this.notificador = new NotificadorReserva();
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

        notificador.notificarConfirmacion(r);

        r.confirmar();

        return total;
    }
}

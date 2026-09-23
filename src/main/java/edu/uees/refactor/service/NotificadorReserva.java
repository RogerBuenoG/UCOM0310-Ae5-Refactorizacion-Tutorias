package edu.uees.refactor.service;

import edu.uees.refactor.domain.Reserva;

/**
 * Responsabilidad: gestionar la notificación simulada de una reserva.
 */
public class NotificadorReserva {

    public void notificarConfirmacion(Reserva reserva) {
        System.out.println(
                "Correo enviado a " + reserva.getCorreo()
        );
    }
}

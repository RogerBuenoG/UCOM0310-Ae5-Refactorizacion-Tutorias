package edu.uees.refactor.service;

import edu.uees.refactor.domain.Reserva;

/**
 * Responsabilidad: validar las precondiciones necesarias para procesar una reserva.
 */
public class ValidadorReserva {

    public boolean esValida(Reserva reserva, int horasAnticipacion) {
        if (reserva == null) {
            return false;
        }

        if (reserva.getCorreo() == null
                || !reserva.getCorreo().contains("@")) {
            return false;
        }

        if (reserva.getInicio() == null
                || reserva.getFin() == null
                || !reserva.getFin().isAfter(reserva.getInicio())) {
            return false;
        }

        return horasAnticipacion >= 2;
    }
}

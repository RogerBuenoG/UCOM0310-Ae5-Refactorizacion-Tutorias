package edu.uees.refactor.service;

import edu.uees.refactor.domain.Reserva;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidadorReservaTest {

    private final ValidadorReserva validador = new ValidadorReserva();

    @Test
    void reservaValidaConCincoHorasEsAceptada() {
        assertTrue(validador.esValida(reservaValida(), 5));
    }

    @Test
    void dosHorasEsElLimiteValido() {
        assertTrue(validador.esValida(reservaValida(), 2));
    }

    @Test
    void unaHoraNoPermiteProcesar() {
        assertFalse(validador.esValida(reservaValida(), 1));
    }

    @Test
    void correoInvalidoNoEsAceptado() {
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        Reserva reserva = new Reserva(
                "R-INVALIDO",
                "correo-invalido",
                inicio,
                inicio.plusHours(1),
                "NORMAL"
        );
        assertFalse(validador.esValida(reserva, 5));
    }

    private Reserva reservaValida() {
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        return new Reserva(
                "R-VALIDA",
                "ana@uees.edu.ec",
                inicio,
                inicio.plusHours(1),
                "NORMAL"
        );
    }
}

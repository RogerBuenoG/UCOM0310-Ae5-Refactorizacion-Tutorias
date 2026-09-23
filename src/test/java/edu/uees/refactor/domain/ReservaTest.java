package edu.uees.refactor.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservaTest {

    @Test
    void reservaNormalConservaTarifaBase() {
        Reserva reserva = reserva("NORMAL");
        assertEquals(40.0, reserva.calcularTotal(), 0.001);
    }

    @Test
    void reservaVipConservaDescuentoActual() {
        Reserva reserva = reserva("VIP");
        assertEquals(34.0, reserva.calcularTotal(), 0.001);
    }

    private Reserva reserva(String tipo) {
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        return new Reserva(
                "R-TARIFA",
                "ana@uees.edu.ec",
                inicio,
                inicio.plusHours(1),
                tipo
        );
    }
}

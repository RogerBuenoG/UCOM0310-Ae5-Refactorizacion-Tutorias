package edu.uees.refactor.service;

import edu.uees.refactor.domain.EstadoReserva;
import edu.uees.refactor.domain.Reserva;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioReservasTest {
    @Test
    void reservaNormalValidaSeConfirmaYDevuelve40() {
        // Arrange
        Reserva reserva = reservaValida("NORMAL");
        ServicioReservas servicio = new ServicioReservas();
        // Act
        double total = servicio.procesar(reserva, 5);
        // Assert
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        assertEquals(40.0, total, 0.001);
    }

    @Test
    void reservaVipValidaSeConfirmaYDevuelve34() {
        // Arrange
        Reserva reserva = reservaValida("VIP");
        ServicioReservas servicio = new ServicioReservas();
        // Act
        double total = servicio.procesar(reserva, 5);
        // Assert
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        assertEquals(34.0, total, 0.001);
    }

    @Test
    void correoInvalidoRetornaCeroYNoConfirma() {
        // Arrange
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        Reserva reserva = new Reserva("R-CORREO", "correo-invalido", inicio, inicio.plusHours(1), "NORMAL");
        ServicioReservas servicio = new ServicioReservas();
        // Act
        double total = servicio.procesar(reserva, 5);
        // Assert
        assertEquals(0.0, total, 0.001);
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
    }

    @Test
    void periodoInvalidoRetornaCeroYNoConfirma() {
        // Arrange
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        Reserva reserva = new Reserva("R-PERIODO", "ana@uees.edu.ec", inicio, inicio, "NORMAL");
        ServicioReservas servicio = new ServicioReservas();
        // Act
        double total = servicio.procesar(reserva, 5);
        // Assert
        assertEquals(0.0, total, 0.001);
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
    }

    @Test
    void limiteDeDosHorasSeProcesaCorrectamente() {
        // Arrange
        Reserva reserva = reservaValida("NORMAL");
        ServicioReservas servicio = new ServicioReservas();
        // Act
        double total = servicio.procesar(reserva, 2);
        // Assert
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        assertEquals(40.0, total, 0.001);
    }

    @Test
    void limiteDeUnaHoraRetornaCeroYNoConfirma() {
        // Arrange
        Reserva reserva = reservaValida("NORMAL");
        ServicioReservas servicio = new ServicioReservas();
        // Act
        double total = servicio.procesar(reserva, 1);
        // Assert
        assertEquals(0.0, total, 0.001);
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
    }

    @Test
    void reservaNulaRetornaCeroComoCondicionEspecial() {
        // Arrange
        ServicioReservas servicio = new ServicioReservas();
        // Act
        double total = servicio.procesar(null, 5);
        // Assert
        assertEquals(0.0, total, 0.001);
    }

    private Reserva reservaValida(String tipo) {
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        return new Reserva("R-TEST", "ana@uees.edu.ec", inicio, inicio.plusHours(1), tipo);
    }
}

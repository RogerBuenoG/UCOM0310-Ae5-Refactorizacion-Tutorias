# Laboratorio 2 — Red de seguridad con JUnit 5

Se incorporan pruebas de caracterización del comportamiento actual de `ServicioReservas`, organizadas con Arrange → Act → Assert.

## Escenarios protegidos
1. NORMAL válida: confirma y devuelve 40.
2. VIP válida: confirma y devuelve 34.
3. Correo inválido: devuelve 0 y permanece PENDIENTE.
4. Periodo inválido: devuelve 0 y permanece PENDIENTE.
5. Anticipación de 2 horas: se procesa correctamente.
6. Anticipación de 1 hora: devuelve 0 y permanece PENDIENTE.
7. Reserva nula: devuelve 0 como condición especial.

## Primera refactorización protegida
El cálculo de la tarifa se extrajo al método privado `calcularTotal(Reserva)` sin cambiar la regla existente. La suite debe permanecer verde después del cambio.

## Experimento de regresión
Cambiar temporalmente `0.85` por `0.80`, ejecutar `mvn clean test`, observar el fallo de la prueba VIP y restaurar `0.85`. El cambio experimental no debe quedar en la versión final.

## Evidencia local
Agregar la captura/salida real de `mvn clean test` y la evidencia del commit realizado en el equipo del estudiante.

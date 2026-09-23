# Informe técnico — Actividad Evaluada 3 (Ae5)
## Refactorización respaldada por pruebas unitarias

### 1. Datos de la actividad

- **Curso:** Diseño de Software / UCOM0310
- **Actividad:** Ae5 — Refactorización respaldada por pruebas unitarias
- **Proyecto:** Sistema de reservas de tutorías
- **Repositorio:** `RogerBuenoG/UCOM0310-Ae5-Refactorizacion-Tutorias`
- **Estado:** implementación final y evidencia técnica

### 2. Problema inicial

El sistema heredado concentraba varias responsabilidades en `ServicioReservas`: validación de entradas, cálculo de tarifa, simulación de persistencia, notificación y coordinación del cambio de estado.

La línea base permitió observar que el comportamiento dependía de reglas concretas: NORMAL devuelve 40, VIP devuelve 34, la anticipación mínima válida es de 2 horas, y entradas inválidas devuelven 0 sin confirmar la reserva.

### 3. Red de seguridad

La Actividad 2 dejó siete pruebas de caracterización:

- NORMAL válida.
- VIP válida.
- Correo inválido.
- Periodo inválido.
- Anticipación de 2 horas.
- Anticipación de 1 hora.
- Reserva nula.

La suite se ejecutó antes de refactorizar y obtuvo:

```text
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### 4. Diagnóstico

Los principales problemas identificados fueron:

1. Responsabilidades múltiples en `ServicioReservas`.
2. Cálculo de tarifa ubicado lejos de los datos de `Reserva`.
3. Validaciones de correo, periodo y anticipación mezcladas con la coordinación.
4. Notificación simulada dentro del servicio.
5. Condicionales de negocio concentrados en el método de procesamiento.

### 5. Refactorización 1 — Move Method

**Cambio:** `calcularTotal()` pasó de `ServicioReservas` a `Reserva`.

**Justificación:** la regla utiliza el atributo `tipo` de `Reserva`, por lo que el comportamiento queda más próximo a los datos que necesita.

**Preservación:** NORMAL continúa devolviendo 40 y VIP 34.

**Commit esperado:**

```text
refactor: mover calculo de tarifa a Reserva
```

### 6. Refactorización 2 — Extract Class

**Cambio:** se creó `ValidadorReserva`.

La clase encapsula las validaciones de:

- reserva nula;
- correo;
- periodo;
- anticipación mínima.

`ServicioReservas` delega la validación y conserva el mismo resultado para cada escenario.

**Commit esperado:**

```text
refactor: extraer validacion de reservas
```

### 7. Refactorización 3 — Extract Class

**Cambio:** se creó `NotificadorReserva`.

La impresión del correo de confirmación deja de estar directamente en `ServicioReservas`. El servicio conserva la coordinación del proceso y delega la notificación.

**Commit esperado:**

```text
refactor: extraer notificacion de reservas
```

### 8. Comparación antes / después

| Dimensión | Antes | Después |
|---|---|---|
| Validación | ServicioReservas | ValidadorReserva |
| Tarifa | ServicioReservas | Reserva |
| Notificación | ServicioReservas | NotificadorReserva |
| Coordinación | ServicioReservas | ServicioReservas |
| Estado | Reserva | Reserva |
| Pruebas | 7 de caracterización | 7 + pruebas focalizadas |
| Acoplamiento de reglas | Concentrado | Distribuido por responsabilidad |
| Comportamiento | Base observable | Conservado |

### 9. Verificación final

Ejecutar en el proyecto:

```bash
mvn clean test
```

La evidencia final debe mostrar `BUILD SUCCESS` y cero fallos.

También verificar:

```bash
git status
git log --oneline --decorate -10
```

### 10. Conclusión

Las tres refactorizaciones reorganizan responsabilidades existentes sin cambiar las reglas funcionales. La red de seguridad permite comprobar que los resultados y estados observados se mantienen. La evolución se documenta mediante pruebas, cambios pequeños y commits separados.

### 11. Declaración de uso de IA

Se utilizó una herramienta de IA como apoyo para comprender el código, proponer estructuras de refactorización, revisar redacción y organizar documentación. La ejecución de comandos, verificación de pruebas, revisión del resultado y entrega del repositorio deben ser realizadas y validadas por el estudiante.

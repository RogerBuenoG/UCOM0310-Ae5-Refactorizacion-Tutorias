# Guía de commits y evidencia — Ae5

## Historial objetivo

El repositorio debe mostrar la evolución incremental:

```text
test: caracterizar comportamiento heredado
refactor: mover calculo de tarifa a Reserva
refactor: extraer validacion de reservas
refactor: extraer notificacion de reservas
docs: completar reporte final Ae5
```

## Comandos de verificación

```bash
git status
git log --oneline --decorate -10
mvn clean test
```

## Si los cambios están todos pendientes

Para conservar la separación de commits, realizar cada cambio por etapas y ejecutar la suite después de cada etapa. No se recomienda crear un único commit que mezcle las tres refactorizaciones.

## Evidencias recomendadas

1. Captura de `mvn clean test` antes de la primera refactorización.
2. Captura de `git log --oneline --decorate -10`.
3. Captura de la primera refactorización y su prueba verde.
4. Captura de la segunda refactorización y su prueba verde.
5. Captura de la tercera refactorización y su prueba verde.
6. Captura del repositorio GitHub con el historial.
7. Captura final de `mvn clean test`.
8. Tabla antes/después incluida en `09_REPORTE_AE5.md`.

> No se deben inventar resultados ni capturas. Las salidas deben corresponder a ejecuciones reales en el equipo del estudiante.

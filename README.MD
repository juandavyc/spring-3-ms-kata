# Evaluación de Participantes

Proyecto para la evaluación de participantes mediante jueces, los cuales realizan evaluaciones basadas en criterios como puntaje, puntos extra, etc., para posteriormente generar un reporte en Atlassian.

---

## Tecnologías

- **Frontend:** Angular 20, NgRx, PrimeNG, Tailwind 4  
- **Backend:** Spring Boot 3, Microservicios, Kafka 3.7.1  
- **Base de Datos:** PostgreSQL 16 (pasiva)

---

## Flujo del Sistema

1. **Creación de entidades**
   - `Participante`: nombre, email, rol  
   - `Jurado`: nombre, email, especialidad  
   Ambos deben estar creados antes de poder realizar evaluaciones.

2. **Evaluación (``)**
   - Los jueces evalúan a los participantes según:
     - Perfil  
     - Habilidad comunicativa  
     - Conocimiento técnico  

3. **Ranking (``)**
   - Se muestran los **tres primeros participantes** según la puntuación total.

4. **Reporte (``)**
   - Al pulsar el botón **Crear Snapshot**, se genera un reporte y se sube automáticamente a Atlassian.

---

Juan david yara cifuentes



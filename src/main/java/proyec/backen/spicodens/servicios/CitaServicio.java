package proyec.backen.spicodens.servicios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyec.backen.spicodens.modelo.Cita;
import proyec.backen.spicodens.repositorio.CitaRepositorio;



@Service
public class CitaServicio {

    @Autowired
    private CitaRepositorio citaRepositorio;

    // ====== Horarios fijos ======
    private static final LocalTime MANANA_INICIO = LocalTime.of(8, 0);
    private static final LocalTime MANANA_FIN = LocalTime.of(12, 0);

    private static final LocalTime TARDE_INICIO = LocalTime.of(15, 0);
    private static final LocalTime TARDE_FIN = LocalTime.of(19, 0);

    public Cita crearCita(Cita cita) {

        LocalTime hora = cita.getHora();

        // 1. Validar que la hora sea 00 o 30
        if (!esBloqueValido(hora)) {
            throw new IllegalArgumentException("La cita debe ser en intervalos de 30 minutos.");
        }

        // 2. Validar horario laboral
        if (!esHorarioValido(hora)) {
            throw new IllegalArgumentException("La hora no está dentro del horario laboral permitido.");
        }

        // 3. Evitar duplicación
        boolean ocupado = citaRepositorio
                .existsByProfesionalIdAndFechaAndHora(
                        cita.getProfesional().getId(),
                        cita.getFecha(),
                        hora
                );

        if (ocupado) {
            throw new IllegalArgumentException("El profesional ya tiene una cita asignada en ese horario.");
        }

        // 4. Guardar
        return citaRepositorio.save(cita);
    }

    public List<LocalTime> obtenerHorasDisponibles(Long profesionalId, LocalDate fecha) {

        // 1. Generar todos los bloques posibles
        List<LocalTime> bloques = generarBloques();

        // 2. Conseguir los horarios ocupados para ese profesional en esa fecha
        List<LocalTime> ocupados = citaRepositorio
                .findHorasOcupadas(profesionalId, fecha);

        // 3. Filtrar
        return bloques.stream()
                .filter(h -> !ocupados.contains(h))
                .toList();
    }



    // ------ Valida si hora termina en :00 o :30 ------
    private boolean esBloqueValido(LocalTime hora) {
        return hora.getMinute() == 0 || hora.getMinute() == 30;
    }

    // ------ Valida si la hora está dentro del horario laboral ------
    private boolean esHorarioValido(LocalTime hora) {
        boolean enManana = !hora.isBefore(MANANA_INICIO) && hora.isBefore(MANANA_FIN);
        boolean enTarde = !hora.isBefore(TARDE_INICIO) && hora.isBefore(TARDE_FIN);
        return enManana || enTarde;
    }

    // ------ Genera todos los bloques de 30 minutos ------
    private List<LocalTime> generarBloques() {

        List<LocalTime> lista = new ArrayList<>();

        // Bloques de la mañana
        LocalTime h = MANANA_INICIO;
        while (h.isBefore(MANANA_FIN)) {
            lista.add(h);
            h = h.plusMinutes(30);
        }

        // Bloques de la tarde
        h = TARDE_INICIO;
        while (h.isBefore(TARDE_FIN)) {
            lista.add(h);
            h = h.plusMinutes(30);
        }

        return lista;
    }
}

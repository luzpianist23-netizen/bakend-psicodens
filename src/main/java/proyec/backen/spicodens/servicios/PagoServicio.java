package proyec.backen.spicodens.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyec.backen.spicodens.modelo.Pago;
import proyec.backen.spicodens.modelo.Recepcionista;
import proyec.backen.spicodens.modelo.Tratamiento;
import proyec.backen.spicodens.repositorio.PagoRepositorio;
import proyec.backen.spicodens.repositorio.RecepcionistaRepositorio;
import proyec.backen.spicodens.repositorio.TratamientoRepositorio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PagoServicio {

    @Autowired
    private PagoRepositorio PagoRepositorio;

    @Autowired
    private TratamientoRepositorio tratamientoRepositorio;

    @Autowired
    private RecepcionistaRepositorio recepcionistaRepositorio;

    

    public Pago registrarPago(Long tratamientoId, Long recepcionistaId, Double monto) {
        Tratamiento tratamiento = tratamientoRepositorio.findById(tratamientoId)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));

        Recepcionista recepcionista = recepcionistaRepositorio.findById(recepcionistaId)
                .orElseThrow(() -> new RuntimeException("Recepcionista no encontrado"));

        Double totalPagado = PagoRepositorio.totalPagadoPorTratamiento(tratamiento);
        if (totalPagado == null) totalPagado = 0.0;

        Double precioBase = 0.0;
        if (tratamiento.getServicioOdontologico() != null)
            precioBase = tratamiento.getServicioOdontologico().getPrecio();
        else if (tratamiento.getServicioPsicologico() != null)
            precioBase = tratamiento.getServicioPsicologico().getPrecio();

        Double nuevoTotal = totalPagado + monto;
        Double saldoRestante = precioBase - nuevoTotal;

        // Crear el pago
        Pago pago = new Pago();
        pago.setTratamiento(tratamiento);
        pago.setRecepcionista(recepcionista);
        pago.setMonto(monto);
        pago.setFecha(LocalDateTime.now());
        pago.setEstado(false);

        // Si se completó el pago total
        if (saldoRestante <= 0) {
            pago.setEstado(true);
            tratamiento.setEstado(true);
            tratamientoRepositorio.save(tratamiento);
        }

        return PagoRepositorio.save(pago);
    }

    public List<Pago> listarPagosPorTratamiento(Long tratamientoId) {
        Tratamiento tratamiento = tratamientoRepositorio.findById(tratamientoId)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));
        return PagoRepositorio.findByTratamiento(tratamiento);
    }

    public Double obtenerTotalPagado(Long tratamientoId) {
        Tratamiento tratamiento = tratamientoRepositorio.findById(tratamientoId)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado"));
        Double total = PagoRepositorio.totalPagadoPorTratamiento(tratamiento);
        return total != null ? total : 0.0;
    }

    public List<Map<String, Object>> listarTratamientosConSaldo() {
    List<Tratamiento> tratamientos = tratamientoRepositorio.findAllConServiciosYPaciente();
    List<Map<String, Object>> resultado = new ArrayList<>();

    for (Tratamiento t : tratamientos) {
        Double precio = 0.0;
        String servicio = "";
        String tipoServicio = "";

        if (t.getServicioOdontologico() != null) {
            precio = t.getServicioOdontologico().getPrecio();
            servicio = t.getServicioOdontologico().getNombre();
            tipoServicio = "Odontológico";
        } else if (t.getServicioPsicologico() != null) {
            precio = t.getServicioPsicologico().getPrecio();
            servicio = t.getServicioPsicologico().getNombre();
            tipoServicio = "Psicológico";
        }

        Double totalPagado = obtenerTotalPagado(t.getId());
        Double saldo = precio - totalPagado;
        if (saldo < 0) saldo = 0.0;

        Map<String, Object> item = new LinkedHashMap<>();
        item.put("idTratamiento", t.getId());
        item.put("paciente", t.getPaciente().getPersona().getNombre() + " " +
                              t.getPaciente().getPersona().getApellidoPaterno() + " " +
                              t.getPaciente().getPersona().getApellidoMaterno());
        item.put("servicio", servicio);
        item.put("tipoServicio", tipoServicio);
        item.put("precio", precio);
        item.put("totalPagado", totalPagado);
        item.put("saldoPendiente", saldo);
        item.put("pagado", saldo == 0);

        resultado.add(item);
    }

    return resultado;
}

}

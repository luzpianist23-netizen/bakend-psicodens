package proyec.backen.spicodens.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import proyec.backen.spicodens.modelo.Pago;
import proyec.backen.spicodens.servicios.PagoServicio;

import java.util.*;


@RestController
@RequestMapping("/api/pagos")
public class PagoControlador {

    @Autowired
    private PagoServicio pagoServicio;

    @GetMapping("/tratamientosActivos")
    public ResponseEntity<List<Map<String, Object>>> listarTratamientosConSaldo() {
        return ResponseEntity.ok(pagoServicio.listarTratamientosConSaldo());
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarPago(@RequestBody Map<String, Object> body) {
        Long tratamientoId = Long.valueOf(body.get("tratamientoId").toString());
        Long recepcionistaId = Long.valueOf(body.get("recepcionistaId").toString());
        Double monto = Double.valueOf(body.get("monto").toString());

        Pago pago = pagoServicio.registrarPago(tratamientoId, recepcionistaId, monto);

        Double totalPagado = pagoServicio.obtenerTotalPagado(tratamientoId);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("mensaje", "Pago registrado correctamente");
        response.put("pago", pago);
        response.put("totalPagado", totalPagado);
        response.put("estadoTratamiento", pago.getTratamiento().getEstado());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tratamiento/{id}")
    public ResponseEntity<Map<String, Object>> listarPagosPorTratamiento(@PathVariable Long id) {
        List<Pago> pagos = pagoServicio.listarPagosPorTratamiento(id);
        Double totalPagado = pagoServicio.obtenerTotalPagado(id);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("pagos", pagos);
        response.put("totalPagado", totalPagado);
        return ResponseEntity.ok(response);
    }
}

package proyec.backen.spicodens.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import proyec.backen.spicodens.modelo.Pago;
import proyec.backen.spicodens.modelo.Tratamiento;

import java.util.List;

public interface PagoRepositorio extends JpaRepository<Pago, Long> {

    List<Pago> findByTratamiento(Tratamiento tratamiento);

    @Query("SELECT SUM(p.monto) FROM Pago p WHERE p.tratamiento = :tratamiento")
    Double totalPagadoPorTratamiento(Tratamiento tratamiento);
}

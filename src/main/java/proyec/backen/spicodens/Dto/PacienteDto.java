package proyec.backen.spicodens.Dto;

public class PacienteDto {
    private Long id;
    private PersonaDto persona;
    private String tipoPaciente;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public PersonaDto getPersona() {
        return persona;
    }
    public void setPersona(PersonaDto persona) {
        this.persona = persona;
    }
    public String getTipoPaciente() {
        return tipoPaciente;
    }
    public void setTipoPaciente(String tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
    }

    
}
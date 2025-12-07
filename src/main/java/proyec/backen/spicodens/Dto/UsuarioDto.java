package proyec.backen.spicodens.Dto;

public class UsuarioDto {
    private Long id;
    private String username;
    private PersonaDto persona;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public PersonaDto getPersona() {
        return persona;
    }
    public void setPersona(PersonaDto persona) {
        this.persona = persona;
    }

}
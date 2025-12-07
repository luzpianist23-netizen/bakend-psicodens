package proyec.backen.spicodens.Dto;

public class ProfesionalDto {
    private Long id;
    private String matriculaProfesional;
    private String descripcion;
    private Boolean activo;
    private UsuarioDto usuario;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMatriculaProfesional() {
        return matriculaProfesional;
    }
    public void setMatriculaProfesional(String matriculaProfesional) {
        this.matriculaProfesional = matriculaProfesional;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public UsuarioDto getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    
}
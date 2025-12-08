package proyec.backen.spicodens.Dto;

public class RecepcionistaDto {
    private Long id;
    private Boolean activo;
    private UsuarioDto usuario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public UsuarioDto getUsuario() { return usuario; }
    public void setUsuario(UsuarioDto usuario) { this.usuario = usuario; }
}

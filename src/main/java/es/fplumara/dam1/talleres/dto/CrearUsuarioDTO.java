package es.fplumara.dam1.talleres.dto;

public class CrearUsuarioDTO {

    private String nombre;
    private String perfil;
    private String curso;
    private String email;
    private String discordUserId;

    public CrearUsuarioDTO() {}

    public CrearUsuarioDTO(String nombre, String perfil, String curso, String email, String discordUserId) {
        this.nombre = nombre;
        this.perfil = perfil;
        this.curso = curso;
        this.email = email;
        this.discordUserId = discordUserId;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDiscordUserId() { return discordUserId; }
    public void setDiscordUserId(String discordUserId) { this.discordUserId = discordUserId; }
}
package es.fplumara.dam1.talleres.model;


/**
 * - **id**
 *
 *     Identificador interno único del usuario.
 *
 * - **nombre**
 *
 *     Nombre visible del usuario dentro del sistema.
 *
 * - **perfil**
 *
 *     Determina el perfil global del usuario: (PROFESOR, ALUMNO, INVITADO)
 *
 * - **discordUserId** (opcional)
 *
 *     Identificador único del usuario en Discord. Permite futuras integraciones con bots.
 *
 * - **curso** (opcional)
 *
 *     Si es un alumno indica en que curso se encuentra estudiando
 *
 * - **email** (opcional)
 *
 *     Correo electrónico del usuario
 */
package es.fplumara.dam1.talleres.model;

public class Usuario {

    private Long id;
    private String nombre;
    private Perfil perfil;
    private String discordUserId;
    private String curso;
    private String email;

    public Usuario() {}

    public Usuario(Long id, String nombre, Perfil perfil, String discordUserId, String curso, String email) {
        this.id = id;
        this.nombre = nombre;
        this.perfil = perfil;
        this.discordUserId = discordUserId;
        this.curso = curso;
        this.email = email;
    }

    public Usuario(String nombre, Perfil perfil, String discordUserId, String curso, String email) {
        this.nombre = nombre;
        this.perfil = perfil;
        this.discordUserId = discordUserId;
        this.curso = curso;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

    public String getDiscordUserId() { return discordUserId; }
    public void setDiscordUserId(String discordUserId) { this.discordUserId = discordUserId; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
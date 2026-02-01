package es.fplumara.dam1.talleres.repository;

import es.fplumara.dam1.talleres.model.Usuario;

public interface UsuarioRepository {

    Usuario save(Usuario usuario);

    Usuario findById(Long id);

    Usuario findByEmail(String email);

    Usuario findByDiscordUserId(String discordUserId);

    List<Usuario> findAll();

    void deleteById(Long id);

}
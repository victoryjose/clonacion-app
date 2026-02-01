package es.fplumara.dam1.talleres.repository.impl;

import es.fplumara.dam1.talleres.model.Usuario;
import es.fplumara.dam1.talleres.repository.UsuarioRepository;

import java.util.*;

public class InMemoryUserRepository implements UsuarioRepository {

    private Long contador = 0L;
    private Map<Long, Usuario> usuarios = new HashMap<>();

    @Override
    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            usuario.setId(contador);
            contador++;
        }
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    @Override
    public Usuario findById(Long id) {
        return usuarios.get(id);
    }

    @Override
    public Usuario findByEmail(String email) {
        for (Usuario u : usuarios.values()) {
            if (u.getEmail() != null && u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public Usuario findByDiscordUserId(String discordUserId) {
        for (Usuario u : usuarios.values()) {
            if (u.getDiscordUserId() != null && u.getDiscordUserId().equals(discordUserId)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>(usuarios.values());
    }

    @Override
    public void deleteById(Long id) {
        usuarios.remove(id);
    }
}
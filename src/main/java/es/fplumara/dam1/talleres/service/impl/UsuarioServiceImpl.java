package es.fplumara.dam1.talleres.service.impl;

import es.fplumara.dam1.talleres.dto.CrearUsuarioDTO;
import es.fplumara.dam1.talleres.dto.ActualizarUsuarioDTO;
import es.fplumara.dam1.talleres.exception.DatosTallerException;
import es.fplumara.dam1.talleres.exception.NotFoundException;
import es.fplumara.dam1.talleres.model.Inscripcion;
import es.fplumara.dam1.talleres.model.Perfil;
import es.fplumara.dam1.talleres.model.Usuario;
import es.fplumara.dam1.talleres.repository.UsuarioRepository;
import es.fplumara.dam1.talleres.repository.InscripcionRepository;
import es.fplumara.dam1.talleres.service.UsuarioService;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;
    private InscripcionRepository inscripcionRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, InscripcionRepository inscripcionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    @Override
    public Usuario crearUsuario(CrearUsuarioDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new DatosTallerException("nombre requerido");
        }

        if (dto.getCurso() != null && dto.getPerfil() != null && !dto.getPerfil().equals("ALUMNO")) {
            throw new DatosTallerException("curso solo permitido para perfil ALUMNO");
        }

        if (dto.getEmail() != null) {
            Usuario u = usuarioRepository.findByEmail(dto.getEmail());
            if (u != null) {
                throw new DatosTallerException("email duplicado");
            }
        }

        if (dto.getDiscordUserId() != null) {
            Usuario u = usuarioRepository.findByDiscordUserId(dto.getDiscordUserId());
            if (u != null) {
                throw new DatosTallerException("discord duplicado");
            }
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setPerfil(Perfil.valueOf(dto.getPerfil()));
        usuario.setCurso(dto.getCurso());
        usuario.setEmail(dto.getEmail());
        usuario.setDiscordUserId(dto.getDiscordUserId());

        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new NotFoundException("usuario no encontrado");
        }
        return usuario;
    }

    @Override
    public Usuario actualizarUsuario(Long id, ActualizarUsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new NotFoundException("usuario no encontrado");
        }

        if (dto.getNombre() != null) usuario.setNombre(dto.getNombre());
        if (dto.getPerfil() != null) usuario.setPerfil(Perfil.valueOf(dto.getPerfil()));
        if (dto.getCurso() != null) usuario.setCurso(dto.getCurso());
        if (dto.getEmail() != null) usuario.setEmail(dto.getEmail());
        if (dto.getDiscordUserId() != null) usuario.setDiscordUserId(dto.getDiscordUserId());

        if (usuario.getCurso() != null && usuario.getPerfil() != Perfil.ALUMNO) {
            throw new DatosTallerException("curso solo permitido para perfil ALUMNO");
        }

        if (dto.getEmail() != null) {
            Usuario u = usuarioRepository.findByEmail(dto.getEmail());
            if (u != null && !u.getId().equals(id)) {
                throw new DatosTallerException("email duplicado");
            }
        }

        if (dto.getDiscordUserId() != null) {
            Usuario u = usuarioRepository.findByDiscordUserId(dto.getDiscordUserId());
            if (u != null && !u.getId().equals(id)) {
                throw new DatosTallerException("discord duplicado");
            }
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new NotFoundException("usuario no encontrado");
        }

        List<Inscripcion> inscripciones = inscripcionRepository.findByUsuarioId(id);
        for (Inscripcion i : inscripciones) {
            inscripcionRepository.deleteById(i.getId());
        }

        usuarioRepository.deleteById(id);
    }
}
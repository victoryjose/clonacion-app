package es.fplumara.dam1.talleres.repository;

import es.fplumara.dam1.talleres.model.Inscripcion;

public interface InscripcionRepository {

    public Inscripcion save(Inscripcion incripcion);

    public Inscripcion findById(String id);

    public void findByTallerIdAndUsuarioId(Long tallerId,Long usuarioId);

    public List<Inscripcion> findByTallerId(Long tallerId);

    public List<Inscripcion> findByUsuarioId(Long usuarioId);

    public List<Inscripcion> findByTallerAndRol(Long tallerId, Rol rol);

    public void deleteById(String id);

    public void deleteByTallerIdAndUsuarioId(Long tallerId, Long usuarioId);

}
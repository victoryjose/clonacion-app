package es.fplumara.dam1.talleres.repository.impl;
import es.fplumara.dam1.talleres.repository.InscripcionRepository;
import es.fplumara.dam1.talleres.model.Inscripcion;
import java.util.HashMap;
import java.util.Map;

public class InMemoryInscripcionRepository implements InscripcionRepository {

    private Long contador = 0L;

    private Map<String,Inscripcion> inscripciones;

    public InMemoryInscripcionRepository() {
        inscripciones = new HashMap<>();
    }

    @Override
    public Inscripcion save(Inscripcion inscripcion) {
        if(inscripcion.getId()==null) {
            inscripciones.setId(contador);
            contador++;
        }
        inscripciones.put(inscripcion.getId(), inscripcion);
        return inscripcion;
    }

    @Override
    public Inscripcion findById(String id) {
        return Inscripciones.get(id);
    }

    @Override
    public Inscripcion findByTallerIdAndUsuarioId(Long tallerId, Long usuarioId) {
        String inscripcionId = tallerId.toString()+"/"+usuarioId.toString();
        return inscripciones.get(inscripcionId);
    }

    @Override
    public List<Inscripcion> findByTallerId(Long tallerId){
        List<Inscripcion> inscripcionesPorTaller = new ArrayList<>();
        for (Inscripcion i : inscripciones.values){
            if (i.getTallerId().equals(tallerId)){
                inscripcionesPorTaller.add(i);
            }
        }
        return inscripcionesPorTaller;
    }

    @Override
    public List<Inscripcion> findByUsuarioId(Long usuarioId){
        List<Inscripcion> inscripcionesPorUsuario = new ArrayList<>();
        for (Inscripcion i : inscripciones.values){
            if (i.getUsuarioId().equals(usuarioId)){
                inscripcionesPorUsuario.add(i);
            }
        }
        return inscripcionesPorUsuario;
    }

    @Override
    public List<Inscripciion> findByTallerAndRol(Long tallerId, Rol rol){
        List<Inscripcion> inscripcionesPorRolDeTaller = new ArrayList<>();
        for (Inscripcion i : inscripciones.values){
            if (i.getTallerId().equals(tallerId) && i.getRol().equals(rol)){
                inscripcionesPorRolDeTaller.add(i);
            }
        }
        return inscripcionesPorRolDeTaller;
    }

    @Override
    public void deleteById(String id) {
        Inscripciones.remove(id);
    }

    @Override
    public Inscripcion deleteByTallerIdAndUsuarioId(Long tallerId, Long usuarioId) {
        String inscripcionId = tallerId.toString() + "/" + usuarioId.toString();
        Inscripciones.remover(inscripcionId);
    }

}
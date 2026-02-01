package es.fplumara.dam1.talleres.service.impl;

import es.fplumara.dam1.talleres.dto.ActualizarTallerDTO;
import es.fplumara.dam1.talleres.dto.CrearTallerDTO;
import es.fplumara.dam1.talleres.exception.DatosTallerException;
import es.fplumara.dam1.talleres.exception.NotFoundException;
import es.fplumara.dam1.talleres.model.EstadoInscripcion;
import es.fplumara.dam1.talleres.model.Inscripcion;
import es.fplumara.dam1.talleres.model.Rol;
import es.fplumara.dam1.talleres.model.Taller;
import es.fplumara.dam1.talleres.repository.InscripcionRepository;
import es.fplumara.dam1.talleres.repository.TallerRepository;
import es.fplumara.dam1.talleres.service.TallerService;

import java.util.List;

public class TallerServiceImpl implements TallerService {

    private TallerRepository tallerRepository;
    private InscripcionRepository inscripcionRepository;

    public TallerServiceImpl(TallerRepository tallerRepository, InscripcionRepository inscripcionRepository) {
        this.tallerRepository = tallerRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    @Override
    public Taller createTaller(CrearTallerDTO dto) {
        if (dto.getTitulo() == null || dto.getTitulo().trim().isEmpty()) {
            throw new DatosTallerException("titulo requerido");
        }
        if (dto.getCupo() < 0) {
            throw new DatosTallerException("cupo invalido");
        }

        Taller taller = new Taller();
        taller.setTitulo(dto.getTitulo());
        taller.setDescripcion(dto.getDescripcion());
        taller.setUrl(dto.getUrl());
        taller.setLugar(dto.getLugar());
        taller.setCupo(dto.getCupo());

        if (dto.getEstadoInscripcion() != null) {
            taller.setEstadoInscripcion(EstadoInscripcion.valueOf(dto.getEstadoInscripcion()));
        } else {
            taller.setEstadoInscripcion(EstadoInscripcion.ABIERTO);
        }

        return tallerRepository.save(taller);
    }

    @Override
    public List<Taller> listarTalleres() {
        return tallerRepository.findAll();
    }

    @Override
    public Taller obtenerTaller(Long id) {
        Taller taller = tallerRepository.findById(id);
        if (taller == null) {
            throw new NotFoundException("taller no encontrado");
        }
        return taller;
    }

    @Override
    public Taller atualizarTaller(Long id, ActualizarTallerDTO dto) {
        Taller taller = tallerRepository.findById(id);
        if (taller == null) {
            throw new NotFoundException("taller no encontrado");
        }

        if (dto.getTitulo() != null && dto.getTitulo().trim().isEmpty()) {
            throw new DatosTallerException("titulo requerido");
        }

        if (dto.getTitulo() != null) taller.setTitulo(dto.getTitulo());
        if (dto.getDescripcion() != null) taller.setDescripcion(dto.getDescripcion());
        if (dto.getUrl() != null) taller.setUrl(dto.getUrl());
        if (dto.getLugar() != null) taller.setLugar(dto.getLugar());

        if (dto.getEstadoInscripcion() != null) {
            taller.setEstadoInscripcion(EstadoInscripcion.valueOf(dto.getEstadoInscripcion()));
        }

        if (dto.getCupo() < 0) {
            throw new DatosTallerException("cupo invalido");
        }

        List<Inscripcion> participantes = inscripcionRepository.findByTallerAndRol(id, Rol.PARTICIPANTE);
        if (dto.getCupo() < participantes.size()) {
            throw new DatosTallerException("cupo menor que participantes actuales");
        }

        taller.setCupo(dto.getCupo());

        return tallerRepository.save(taller);
    }

    @Override
    public Taller cambiarEstadoTaller(Long id, EstadoInscripcion estado) {
        Taller taller = tallerRepository.findById(id);
        if (taller == null) {
            throw new NotFoundException("taller no encontrado");
        }
        taller.setEstadoInscripcion(estado);
        return tallerRepository.save(taller);
    }

    @Override
    public void eliminarTaller(Long id) {
        Taller taller = tallerRepository.findById(id);
        if (taller == null) {
            throw new NotFoundException("taller no encontrado");
        }

        List<Inscripcion> inscripciones = inscripcionRepository.findByTallerId(id);
        for (Inscripcion i : inscripciones) {
            inscripcionRepository.deleteById(i.getId());
        }

        tallerRepository.deleteById(id);
    }
}
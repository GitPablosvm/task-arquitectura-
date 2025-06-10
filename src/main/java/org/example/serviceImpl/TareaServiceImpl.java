package org.example.serviceImpl;

import org.example.model.Tarea;
import org.example.repository.TareaRepository;
import org.example.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;


    @Override
    public List<Tarea> obtenerTareasPorUsuario(Long idUsuario) {
        return tareaRepository.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public Tarea crearTarea(Tarea tarea) {
        tarea.setEstado(Tarea.Estado.PENDIENTE);
        return tareaRepository.save(tarea);
    }

    @Override
    public Tarea actualizarTarea(Long id, Tarea tarea) {
        Tarea existe = tareaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No existe la Tarea"));
        existe.setDescripcion(tarea.getDescripcion());
        existe.setHoraInicio(tarea.getHoraInicio());
        existe.setDuracion(tarea.getDuracion());
        return tareaRepository.save(existe);
    }

    @Override
    public void eliminarTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    @Override
    public void completarTarea(Long id) {
        Tarea realizado = tareaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Tarea no encontrada"));
        realizado.setEstado(Tarea.Estado.COMPLETADA);
        tareaRepository.save(realizado);
    }
}

package org.example.service;

import org.example.model.Tarea;
import java.util.List;

public interface TareaService {

    List<Tarea> obtenerTareasPorUsuario(Long usuarioId);

    Tarea crearTarea(Tarea tarea);
    Tarea actualizarTarea(Long id, Tarea tarea);
    void eliminarTarea (Long id);
    void completarTarea (Long id);
}

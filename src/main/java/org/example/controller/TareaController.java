package org.example.controller;

import org.example.model.Tarea;
import org.example.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping("/usuario/{usuarioId}")
    public List<Tarea> obtenerTareasPorUsuario(@PathVariable Long usuarioId){
        return tareaService.obtenerTareasPorUsuario(usuarioId);
    }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea){
        return tareaService.crearTarea(tarea);
    }

    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea){
        return tareaService.actualizarTarea(id, tarea);
    }

    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id){
        tareaService.eliminarTarea(id);
    }

    @PatchMapping("/{id}/completar")
    public void completarTarea(@PathVariable Long id) {
        tareaService.completarTarea(id);
    }
}

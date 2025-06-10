package org.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String descripcion;
    private LocalDateTime horaInicio;
    private Double duracion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "Id_usuario")
    private Usuario usuario;

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    // Constructor
    public Tarea(long id, String descripcion, LocalDateTime horaInicio, Double duracion, Estado estado, Usuario usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.horaInicio = horaInicio;
        this.duracion = duracion;
        this.estado = estado;
        this.usuario = usuario;
    }

    public Tarea() {
    }

    // Enum
    public enum Estado {
        PENDIENTE, COMPLETADA
    }
}

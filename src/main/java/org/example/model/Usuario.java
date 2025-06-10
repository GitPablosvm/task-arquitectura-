package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idUsuario;

    private String nombre;
    private String apellido;
    private String usuario;
    private String email;
    private String pass;

    //Geter and Setter
    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    //Constructores
    public Usuario(long idUsuario, String nombre, String apellido, String usuario, String email, String pass) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.email = email;
        this.pass = pass;
    }

    public Usuario() {
    }
}

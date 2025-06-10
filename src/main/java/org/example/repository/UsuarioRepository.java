package org.example.repository;

import org.example.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Para el login
    Usuario findByUsuario(String usuario);
}

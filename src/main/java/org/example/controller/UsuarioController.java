package org.example.controller;

import org.example.model.Usuario;
import org.example.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    //Login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("usuario");
        String password = credentials.get("pass");

        Usuario usuario = usuarioRepository.findByUsuario(username);

        if (usuario != null && usuario.getPass().equals(password)) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Login exitoso");
            response.put("idUsuario", usuario.getIdUsuario());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

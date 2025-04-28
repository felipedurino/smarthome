package com.smarthome.backend_smarthome.controller;

import com.smarthome.backend_smarthome.DAO.InterfaceUsuario;
import com.smarthome.backend_smarthome.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private InterfaceUsuario dao;

    @GetMapping("/usuario")
    public List<Usuario> listarUsuario() {
        return (List<Usuario>) dao.findAll();
    }

    @PostMapping("/usuario")
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return dao.save(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = dao.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent()) {
            Usuario u = usuarioExistente.get();
            if (u.getSenha().equals(usuario.getSenha())) {
                return ResponseEntity.ok(u);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
        }
    }
}

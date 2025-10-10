package com.smarthome.backend_smarthome.service;

import com.smarthome.backend_smarthome.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public Optional<Usuario> buscarUsuarioId(long id) {
        return Optional.empty();
    }

    @Override
    public Usuario atualizarUsuario(long id, Usuario usuario) {
        return null;
    }

    @Override
    public void deletarUsuario(long id) {

    }
}

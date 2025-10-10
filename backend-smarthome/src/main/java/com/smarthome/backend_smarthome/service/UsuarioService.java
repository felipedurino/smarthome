package com.smarthome.backend_smarthome.service;

import com.smarthome.backend_smarthome.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public Usuario cadastrarUsuario(Usuario usuario);

    public Optional <Usuario> buscarUsuarioId(long id);

    public Usuario atualizarUsuario(long id, Usuario usuario);

    public void deletarUsuario(long id);




}

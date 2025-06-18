package com.smarthome.backend_smarthome.controller;


import com.smarthome.backend_smarthome.repository.InterfaceResidencias;
import com.smarthome.backend_smarthome.repository.InterfaceUsuario;
import com.smarthome.backend_smarthome.model.Residencias;
import com.smarthome.backend_smarthome.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ResidenciasController {

    @Autowired
    private InterfaceResidencias dao;

    @Autowired
    private InterfaceUsuario usuarioDao; // injeta o DAO de usuários

    @GetMapping("/residencias")
    public List<Residencias> listarResidencias() {
        return (List<Residencias>) dao.findAll();
    }

    @PostMapping("/cadastrarresidencias")
    public Residencias cadastrarResidencia(@RequestBody Residencias residencias) {
        Long idUsuario = residencias.getUsuario().getId_usuarios();

        Usuario usuario = usuarioDao.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + idUsuario + " não encontrado."));

        residencias.setUsuario(usuario);

        return dao.save(residencias);
    }
    @GetMapping("/ProcurarResidencia/{idUsuario}")
    public ResponseEntity<Boolean> procurarResidencia(@PathVariable Long idUsuario) {
        boolean temResidencia = dao.existsByUsuario_id(idUsuario);
        return ResponseEntity.ok(temResidencia);
    }

    @GetMapping("/residenciaPorUsuario/{idUsuario}")
    public ResponseEntity<Residencias> buscarResidenciaPorUsuario(@PathVariable Long idUsuario) {
        return dao.findByUsuario_Id(idUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

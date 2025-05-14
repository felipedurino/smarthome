package com.smarthome.backend_smarthome.controller;

import com.smarthome.backend_smarthome.repository.InterfaceComodo;
import com.smarthome.backend_smarthome.repository.InterfaceResidencias;
import com.smarthome.backend_smarthome.model.Comodos;
import com.smarthome.backend_smarthome.model.Residencias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class ComodoController {

    @Autowired
    private InterfaceComodo dao;

    @Autowired
    private InterfaceResidencias residenciasDao;

    @GetMapping("/comodos")
    public List<Comodos> listarComodos() {
        return (List<Comodos>) dao.findAll();
    }

    @PostMapping("/cadastrarcomodo")
    public List<Comodos> cadastrarComodos(@RequestBody List<Comodos> comodos) {
        return comodos.stream().map(comodo -> {
            Long idResidencia = comodo.getResidencia().getId_residencia();
            Residencias residencia = residenciasDao.findById(idResidencia)
                    .orElseThrow(() -> new RuntimeException("Residência com ID " + idResidencia + " não encontrada."));
            comodo.setResidencia(residencia);
            return dao.save(comodo);
        }).collect(Collectors.toList());
    }
}

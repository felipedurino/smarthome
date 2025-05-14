package com.smarthome.backend_smarthome.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comodos")

public class Comodos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comodo;
    @Column(name = "nome_do_comodo",length = 100, nullable = false)
    private String nome_do_comodo;
    @Column(name = "numero_comodo",length = 100, nullable = true)
    private int numero_comodo;
    @ManyToOne
    @JoinColumn(name = "residencias_id_residencias")
    private Residencias residencia;

    public Long getId_comodo() {
        return id_comodo;
    }

    public void setId_comodo(Long id_comodo) {
        this.id_comodo = id_comodo;
    }

    public String getNome_do_comodo() {
        return nome_do_comodo;
    }

    public void setNome_do_comodo(String nome_do_comodo) {
        this.nome_do_comodo = nome_do_comodo;
    }

    public Residencias getResidencia() {
        return residencia;
    }

    public void setResidencia(Residencias residencia) {
        this.residencia = residencia;
    }

    public int getNumero_comodo() {
        return numero_comodo;
    }

    public void setNumero_comodo(int numero_comodo) {
        this.numero_comodo = numero_comodo;
    }
}

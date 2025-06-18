package com.smarthome.backend_smarthome.model;

import jakarta.persistence.*;

@Entity
@Table(name = "residencias")

public class Residencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_residencia")
    private Long id;
    @Column(name = "rua", nullable = false)
    private String rua;
    @Column(name = "numero_da_casa",length = 1000, nullable = false)
    private String numero_da_casa;
    @Column(name = "cep",length = 8, nullable = false)
    private String cep;
    @Column(name = "estado", length = 2, nullable = false)
    private String estado;
    @ManyToOne
    @JoinColumn(name = "usuario_id_usuarios", nullable = false)
    private Usuario usuario;

    public String getEstado() {return estado; }

    public void setEstado(String estado) {this.estado = estado; }

    public Long getId_residencia() {
        return id;
    }

    public void setId_residencia(Long id_residencia) {
        this.id = id_residencia;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String  getNumero_da_casa() {
        return numero_da_casa;
    }

    public void setNumero_da_casa(String numero_da_casa) {
        this.numero_da_casa = numero_da_casa;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

package com.smarthome.backend_smarthome.model;

import jakarta.persistence.*;

@Entity
@Table(name = "luzes")
public class Luzes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_luzes")
    private Long id;
    @Column(name = "estado_luz", nullable = false)
    private boolean estado;
    @ManyToOne
    @JoinColumn(name = "comodo_id_comodo")
    private Comodos comodo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Comodos getComodo() {
        return comodo;
    }

    public void setComodo(Comodos comodo) {
        this.comodo = comodo;
    }
}

package com.smarthome.backend_smarthome.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "usuarios")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios")
     private Long id;
    @Column(name = "nome",length = 100, nullable = false)
    private String nome;
    @Column(name = "cpf",length = 11, nullable = false)
     private Long cpf;
    @Column(name = "dt_de_nascimento", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dt_de_nascimento;
    @Column(name = "email",length = 100, nullable = false)
    private String email;
    @Column(name = "senha" ,length = 11, nullable = false)
     private String senha ;

    public Long getId_usuarios() {return id;}

    public void setId_usuarios(Long id_usuarios) {this.id = id_usuarios;}

    public String getSenha() {
          return senha;
     }

     public void setSenha(String senha) {
          this.senha = senha;
     }

     public Long getCpf() {
          return cpf;
     }

     public void setCpf(Long cpf) {
          this.cpf = cpf;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getNome() {
          return nome;
     }

     public void setNome(String nome) {
          this.nome = nome;
     }

    public LocalDate getDt_de_nascimento() {
        return dt_de_nascimento;
    }

    public void setDt_de_nascimento(LocalDate dt_de_nascimento) {
        this.dt_de_nascimento = dt_de_nascimento;
    }
}

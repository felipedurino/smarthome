package com.smarthome.backend_smarthome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")

public class cliente {
    @Id
     @GeneratedValue
     private String idCliente;
     private String cpf;
     private String senha ;
     private String email;
     private String nome;
     private LocalDateTime dtNascimento;

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

     public String getSenha() {
          return senha;
     }

     public void setSenha(String senha) {
          this.senha = senha;
     }

     public String getCpf() {
          return cpf;
     }

     public void setCpf(String cpf) {
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

     public LocalDateTime getDtNascimento() {
          return dtNascimento;
     }

     public void setDtNascimento(LocalDateTime dtNascimento) {
          this.dtNascimento = dtNascimento;
     }

}

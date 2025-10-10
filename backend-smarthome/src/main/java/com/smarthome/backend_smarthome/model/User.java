package com.smarthome.backend_smarthome.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
     private Long id;
    @Column(name = "name",length = 100, nullable = false)
    private String name;
    @Column(name = "cpf",length = 11, nullable = false)
     private Long cpf;
    @Column(name = "birth_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @Column(name = "email",length = 100, nullable = false)
    private String email;
    @Column(name = "password" ,length = 11, nullable = false)
     private String password ;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
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

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}

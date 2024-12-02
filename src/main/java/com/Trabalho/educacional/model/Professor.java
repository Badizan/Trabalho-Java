package com.Trabalho.educacional.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    private String email;

    @Size(max = 15, message = "O telefone deve ter no máximo 15 caracteres.")
    private String telefone;

    @Size(max = 100, message = "A especialidade deve ter no máximo 100 caracteres.")
    private String especialidade;

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}

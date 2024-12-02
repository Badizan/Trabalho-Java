package com.Trabalho.educacional.dto;

public class ProfessorRequestDTO {
    private String nome;
    private String email;
    private String telefone; // Novo campo
    private String especialidade; // Novo campo

    // Getters e Setters

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
        return telefone; // Getter para telefone
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone; // Setter para telefone
    }

    public String getEspecialidade() {
        return especialidade; // Getter para especialidade
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade; // Setter para especialidade
    }
}

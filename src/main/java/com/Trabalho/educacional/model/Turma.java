package com.Trabalho.educacional.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O ano é obrigatório.")
    private Integer ano;

    @NotNull(message = "O semestre é obrigatório.")
    private Integer semestre;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}

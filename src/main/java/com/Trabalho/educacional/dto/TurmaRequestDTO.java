package com.Trabalho.educacional.dto;

public class TurmaRequestDTO {
    private String semestre;
    private Integer cursoId;

    // Getters e Setters

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }
}

package com.Trabalho.educacional.dto;

public class NotaRequestDTO {
    private Double valor;
    private Integer disciplinaId;
    private Integer matriculaId;

    // Getters e Setters

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Integer disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Integer getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }
}

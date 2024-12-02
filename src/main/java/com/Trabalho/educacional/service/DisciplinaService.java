package com.Trabalho.educacional.service;

import com.Trabalho.educacional.model.Disciplina;
import com.Trabalho.educacional.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Disciplina getDisciplinaById(Integer id) {
        Optional<Disciplina> disciplina = disciplinaRepository.findById(id);
        return disciplina.orElse(null);
    }

    public Disciplina createDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina updateDisciplina(Integer id, Disciplina disciplinaDetails) {
        Disciplina disciplina = disciplinaRepository.findById(id).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        disciplina.setNome(disciplinaDetails.getNome());
        disciplina.setCurso(disciplinaDetails.getCurso());
        disciplina.setProfessor(disciplinaDetails.getProfessor());
        return disciplinaRepository.save(disciplina);
    }

    public void deleteDisciplina(Integer id) {
        Disciplina disciplina = disciplinaRepository.findById(id).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        disciplinaRepository.delete(disciplina);
    }
}

package com.Trabalho.educacional.service;

import com.Trabalho.educacional.model.Turma;
import com.Trabalho.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public List<Turma> getAllTurmas() {
        return turmaRepository.findAll();
    }

    public Turma getTurmaById(Integer id) {
        Optional<Turma> turma = turmaRepository.findById(id);
        return turma.orElse(null);
    }

    public Turma createTurma(Turma turma) {
        return turmaRepository.save(turma);
    }

    public Turma updateTurma(Integer id, Turma turmaDetails) {
        Turma turma = turmaRepository.findById(id).orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        turma.setSemestre(turmaDetails.getSemestre());
        turma.setCurso(turmaDetails.getCurso());
        return turmaRepository.save(turma);
    }

    public void deleteTurma(Integer id) {
        Turma turma = turmaRepository.findById(id).orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        turmaRepository.delete(turma);
    }
}

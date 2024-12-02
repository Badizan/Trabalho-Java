package com.Trabalho.educacional.service;

import com.Trabalho.educacional.model.Matricula;
import com.Trabalho.educacional.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    public List<Matricula> getAllMatriculas() {
        return matriculaRepository.findAll();
    }

    public Matricula getMatriculaById(Integer id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);
        return matricula.orElse(null);
    }

    public Matricula createMatricula(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    public Matricula updateMatricula(Integer id, Matricula matriculaDetails) {
        Matricula matricula = matriculaRepository.findById(id).orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        matricula.setAluno(matriculaDetails.getAluno());
        matricula.setTurma(matriculaDetails.getTurma());
        return matriculaRepository.save(matricula);
    }

    public void deleteMatricula(Integer id) {
        Matricula matricula = matriculaRepository.findById(id).orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        matriculaRepository.delete(matricula);
    }
}

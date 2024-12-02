package com.Trabalho.educacional.service;

import com.Trabalho.educacional.model.Aluno;
import com.Trabalho.educacional.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno getAlunoById(Integer id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno.orElse(null);
    }

    public Aluno createAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno updateAluno(Integer id, Aluno alunoDetails) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        aluno.setNome(alunoDetails.getNome());
        aluno.setEmail(alunoDetails.getEmail());
        aluno.setMatricula(alunoDetails.getMatricula());
        aluno.setDataNascimento(alunoDetails.getDataNascimento());
        return alunoRepository.save(aluno);
    }

    public void deleteAluno(Integer id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        alunoRepository.delete(aluno);
    }
}

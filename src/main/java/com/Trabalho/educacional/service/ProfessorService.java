package com.Trabalho.educacional.service;

import com.Trabalho.educacional.model.Professor;
import com.Trabalho.educacional.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

    public Professor getProfessorById(Integer id) {
        Optional<Professor> professor = professorRepository.findById(id);
        return professor.orElse(null);
    }

    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor updateProfessor(Integer id, Professor professorDetails) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        professor.setNome(professorDetails.getNome());
        professor.setEmail(professorDetails.getEmail());
        professor.setTelefone(professorDetails.getTelefone());
        professor.setEspecialidade(professorDetails.getEspecialidade());
        return professorRepository.save(professor);
    }

    public void deleteProfessor(Integer id) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        professorRepository.delete(professor);
    }
}

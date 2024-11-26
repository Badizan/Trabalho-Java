package com.Trabalho.educacional.controller;

import com.Trabalho.educacional.model.Professor;
import com.Trabalho.educacional.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @PostMapping
    public Professor save(@RequestBody Professor professor) {
        return professorRepository.save(professor);
    }
}

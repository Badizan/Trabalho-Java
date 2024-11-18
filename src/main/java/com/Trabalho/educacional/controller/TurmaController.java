package com.Trabalho.educacional.controller;

import com.Trabalho.educacional.entity.Turma;
import com.Trabalho.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    @PostMapping
    public Turma save(@RequestBody Turma turma) {
        return turmaRepository.save(turma);
    }
}

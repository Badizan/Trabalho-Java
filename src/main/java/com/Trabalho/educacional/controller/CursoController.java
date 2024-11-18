package com.Trabalho.educacional.controller;

import com.Trabalho.educacional.entity.Curso;
import com.Trabalho.educacional.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @PostMapping
    public Curso save(@RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }
}

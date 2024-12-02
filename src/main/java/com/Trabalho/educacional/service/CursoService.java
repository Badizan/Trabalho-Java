package com.Trabalho.educacional.service;

import com.Trabalho.educacional.model.Curso;
import com.Trabalho.educacional.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso getCursoById(Integer id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.orElse(null);
    }

    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Integer id, Curso cursoDetails) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        curso.setNome(cursoDetails.getNome());
        curso.setCodigo(cursoDetails.getCodigo());
        curso.setCargaHoraria(cursoDetails.getCargaHoraria());
        return cursoRepository.save(curso);
    }

    public void deleteCurso(Integer id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        cursoRepository.delete(curso);
    }
}

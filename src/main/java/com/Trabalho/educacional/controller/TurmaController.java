package com.Trabalho.educacional.controller;

import com.Trabalho.educacional.dto.TurmaRequestDTO;
import com.Trabalho.educacional.dto.TurmaResponseDTO;
import com.Trabalho.educacional.model.Curso;
import com.Trabalho.educacional.model.Turma;
import com.Trabalho.educacional.repository.CursoRepository;
import com.Trabalho.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<TurmaResponseDTO> create(@RequestBody TurmaRequestDTO dto) {
        return cursoRepository.findById(dto.getCursoId()).map(curso -> {
            Turma turma = new Turma();
            turma.setSemestre(dto.getSemestre());
            turma.setCurso(curso);
            Turma savedTurma = turmaRepository.save(turma);

            TurmaResponseDTO responseDTO = new TurmaResponseDTO();
            responseDTO.setId(savedTurma.getId());
            responseDTO.setSemestre(savedTurma.getSemestre());
            responseDTO.setCursoId(curso.getId());

            return ResponseEntity.ok(responseDTO);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TurmaResponseDTO>> getAll() {
        List<TurmaResponseDTO> turmas = turmaRepository.findAll().stream().map(turma -> {
            TurmaResponseDTO dto = new TurmaResponseDTO();
            dto.setId(turma.getId());
            dto.setSemestre(turma.getSemestre());
            dto.setCursoId(turma.getCurso().getId());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> getById(@PathVariable Integer id) {
        return turmaRepository.findById(id)
                .map(turma -> {
                    TurmaResponseDTO dto = new TurmaResponseDTO();
                    dto.setId(turma.getId());
                    dto.setSemestre(turma.getSemestre());
                    dto.setCursoId(turma.getCurso().getId());
                    return ResponseEntity.ok(dto);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponseDTO> update(@PathVariable Integer id, @RequestBody TurmaRequestDTO dto) {
        return turmaRepository.findById(id).flatMap(turma -> cursoRepository.findById(dto.getCursoId()).map(curso -> {
            turma.setSemestre(dto.getSemestre());
            turma.setCurso(curso);
            Turma updatedTurma = turmaRepository.save(turma);

            TurmaResponseDTO responseDTO = new TurmaResponseDTO();
            responseDTO.setId(updatedTurma.getId());
            responseDTO.setSemestre(updatedTurma.getSemestre());
            responseDTO.setCursoId(curso.getId());

            return ResponseEntity.ok(responseDTO);
        })).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

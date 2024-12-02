package com.Trabalho.educacional.controller;

import com.Trabalho.educacional.dto.DisciplinaRequestDTO;
import com.Trabalho.educacional.dto.DisciplinaResponseDTO;
import com.Trabalho.educacional.model.Curso;
import com.Trabalho.educacional.model.Disciplina;
import com.Trabalho.educacional.repository.CursoRepository;
import com.Trabalho.educacional.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> create(@RequestBody DisciplinaRequestDTO dto) {
        return cursoRepository.findById(dto.getCursoId()).map(curso -> {
            Disciplina disciplina = new Disciplina();
            disciplina.setNome(dto.getNome());
            disciplina.setCurso(curso);
            Disciplina savedDisciplina = disciplinaRepository.save(disciplina);

            DisciplinaResponseDTO responseDTO = new DisciplinaResponseDTO();
            responseDTO.setId(savedDisciplina.getId());
            responseDTO.setNome(savedDisciplina.getNome());
            responseDTO.setCursoId(curso.getId());

            return ResponseEntity.ok(responseDTO);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDTO>> getAll() {
        List<DisciplinaResponseDTO> disciplinas = disciplinaRepository.findAll().stream().map(disciplina -> {
            DisciplinaResponseDTO dto = new DisciplinaResponseDTO();
            dto.setId(disciplina.getId());
            dto.setNome(disciplina.getNome());
            dto.setCursoId(disciplina.getCurso().getId());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> getById(@PathVariable Integer id) {
        return disciplinaRepository.findById(id)
                .map(disciplina -> {
                    DisciplinaResponseDTO dto = new DisciplinaResponseDTO();
                    dto.setId(disciplina.getId());
                    dto.setNome(disciplina.getNome());
                    dto.setCursoId(disciplina.getCurso().getId());
                    return ResponseEntity.ok(dto);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> update(@PathVariable Integer id, @RequestBody DisciplinaRequestDTO dto) {
        return disciplinaRepository.findById(id).flatMap(disciplina -> cursoRepository.findById(dto.getCursoId()).map(curso -> {
            disciplina.setNome(dto.getNome());
            disciplina.setCurso(curso);
            Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);

            DisciplinaResponseDTO responseDTO = new DisciplinaResponseDTO();
            responseDTO.setId(updatedDisciplina.getId());
            responseDTO.setNome(updatedDisciplina.getNome());
            responseDTO.setCursoId(curso.getId());

            return ResponseEntity.ok(responseDTO);
        })).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (disciplinaRepository.existsById(id)) {
            disciplinaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

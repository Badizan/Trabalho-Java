package com.Trabalho.educacional.controller;

import com.Trabalho.educacional.dto.CursoRequestDTO;
import com.Trabalho.educacional.dto.CursoResponseDTO;
import com.Trabalho.educacional.model.Curso;
import com.Trabalho.educacional.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<CursoResponseDTO> create(@RequestBody CursoRequestDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.getNome());
        Curso savedCurso = cursoRepository.save(curso);

        CursoResponseDTO responseDTO = new CursoResponseDTO();
        responseDTO.setId(savedCurso.getId());
        responseDTO.setNome(savedCurso.getNome());

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> getAll() {
        List<CursoResponseDTO> cursos = cursoRepository.findAll().stream().map(curso -> {
            CursoResponseDTO dto = new CursoResponseDTO();
            dto.setId(curso.getId());
            dto.setNome(curso.getNome());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> getById(@PathVariable Integer id) {
        return cursoRepository.findById(id)
                .map(curso -> {
                    CursoResponseDTO dto = new CursoResponseDTO();
                    dto.setId(curso.getId());
                    dto.setNome(curso.getNome());
                    return ResponseEntity.ok(dto);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> update(@PathVariable Integer id, @RequestBody CursoRequestDTO dto) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setNome(dto.getNome());
            Curso updatedCurso = cursoRepository.save(curso);

            CursoResponseDTO responseDTO = new CursoResponseDTO();
            responseDTO.setId(updatedCurso.getId());
            responseDTO.setNome(updatedCurso.getNome());

            return ResponseEntity.ok(responseDTO);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

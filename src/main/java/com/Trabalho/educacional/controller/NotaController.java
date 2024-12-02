package com.Trabalho.educacional.controller;

import com.Trabalho.educacional.dto.NotaRequestDTO;
import com.Trabalho.educacional.dto.NotaResponseDTO;
import com.Trabalho.educacional.model.Disciplina;
import com.Trabalho.educacional.model.Matricula;
import com.Trabalho.educacional.model.Nota;
import com.Trabalho.educacional.repository.DisciplinaRepository;
import com.Trabalho.educacional.repository.MatriculaRepository;
import com.Trabalho.educacional.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @PostMapping
    public ResponseEntity<NotaResponseDTO> create(@RequestBody NotaRequestDTO dto) {
        return matriculaRepository.findById(dto.getMatriculaId()).flatMap(matricula ->
                disciplinaRepository.findById(dto.getDisciplinaId()).map(disciplina -> {
                    Nota nota = new Nota();
                    nota.setValor(dto.getValor());
                    nota.setMatricula(matricula);
                    nota.setDisciplina(disciplina);
                    Nota savedNota = notaRepository.save(nota);

                    NotaResponseDTO responseDTO = new NotaResponseDTO();
                    responseDTO.setId(savedNota.getId());
                    responseDTO.setValor(savedNota.getValor());
                    responseDTO.setMatriculaId(matricula.getId());
                    responseDTO.setDisciplinaId(disciplina.getId());

                    return ResponseEntity.ok(responseDTO);
                })
        ).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<NotaResponseDTO>> getAll() {
        List<NotaResponseDTO> notas = notaRepository.findAll().stream().map(nota -> {
            NotaResponseDTO dto = new NotaResponseDTO();
            dto.setId(nota.getId());
            dto.setValor(nota.getValor());
            dto.setMatriculaId(nota.getMatricula().getId());
            dto.setDisciplinaId(nota.getDisciplina().getId());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(notas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaResponseDTO> getById(@PathVariable Integer id) {
        return notaRepository.findById(id)
                .map(nota -> {
                    NotaResponseDTO dto = new NotaResponseDTO();
                    dto.setId(nota.getId());
                    dto.setValor(nota.getValor());
                    dto.setMatriculaId(nota.getMatricula().getId());
                    dto.setDisciplinaId(nota.getDisciplina().getId());
                    return ResponseEntity.ok(dto);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaResponseDTO> update(@PathVariable Integer id, @RequestBody NotaRequestDTO dto) {
        return notaRepository.findById(id).flatMap(nota ->
                matriculaRepository.findById(dto.getMatriculaId()).flatMap(matricula ->
                        disciplinaRepository.findById(dto.getDisciplinaId()).map(disciplina -> {
                            nota.setValor(dto.getValor());
                            nota.setMatricula(matricula);
                            nota.setDisciplina(disciplina);
                            Nota updatedNota = notaRepository.save(nota);

                            NotaResponseDTO responseDTO = new NotaResponseDTO();
                            responseDTO.setId(updatedNota.getId());
                            responseDTO.setValor(updatedNota.getValor());
                            responseDTO.setMatriculaId(matricula.getId());
                            responseDTO.setDisciplinaId(disciplina.getId());

                            return ResponseEntity.ok(responseDTO);
                        })
                )
        ).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (notaRepository.existsById(id)) {
            notaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

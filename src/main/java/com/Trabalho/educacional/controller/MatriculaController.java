package com.Trabalho.educacional.controller;

import com.Trabalho.educacional.dto.MatriculaRequestDTO;
import com.Trabalho.educacional.dto.MatriculaResponseDTO;
import com.Trabalho.educacional.model.Aluno;
import com.Trabalho.educacional.model.Matricula;
import com.Trabalho.educacional.model.Turma;
import com.Trabalho.educacional.repository.AlunoRepository;
import com.Trabalho.educacional.repository.MatriculaRepository;
import com.Trabalho.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> create(@RequestBody MatriculaRequestDTO dto) {
        return alunoRepository.findById(dto.getAlunoId()).flatMap(aluno ->
                turmaRepository.findById(dto.getTurmaId()).map(turma -> {
                    Matricula matricula = new Matricula();
                    matricula.setAluno(aluno);
                    matricula.setTurma(turma);
                    Matricula savedMatricula = matriculaRepository.save(matricula);

                    MatriculaResponseDTO responseDTO = new MatriculaResponseDTO();
                    responseDTO.setId(savedMatricula.getId());
                    responseDTO.setAlunoId(aluno.getId());
                    responseDTO.setTurmaId(turma.getId());

                    return ResponseEntity.ok(responseDTO);
                })
        ).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MatriculaResponseDTO>> getAll() {
        List<MatriculaResponseDTO> matriculas = matriculaRepository.findAll().stream().map(matricula -> {
            MatriculaResponseDTO dto = new MatriculaResponseDTO();
            dto.setId(matricula.getId());
            dto.setAlunoId(matricula.getAluno().getId());
            dto.setTurmaId(matricula.getTurma().getId());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaResponseDTO> getById(@PathVariable Integer id) {
        return matriculaRepository.findById(id)
                .map(matricula -> {
                    MatriculaResponseDTO dto = new MatriculaResponseDTO();
                    dto.setId(matricula.getId());
                    dto.setAlunoId(matricula.getAluno().getId());
                    dto.setTurmaId(matricula.getTurma().getId());
                    return ResponseEntity.ok(dto);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (matriculaRepository.existsById(id)) {
            matriculaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

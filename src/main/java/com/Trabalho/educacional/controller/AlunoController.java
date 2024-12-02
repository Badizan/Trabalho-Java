package com.Trabalho.educacional.controller;

import com.Trabalho.educacional.dto.AlunoRequestDTO;
import com.Trabalho.educacional.dto.AlunoResponseDTO;
import com.Trabalho.educacional.model.Aluno;
import com.Trabalho.educacional.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> create(@RequestBody AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        Aluno savedAluno = alunoRepository.save(aluno);

        AlunoResponseDTO responseDTO = new AlunoResponseDTO();
        responseDTO.setId(savedAluno.getId());
        responseDTO.setNome(savedAluno.getNome());
        responseDTO.setEmail(savedAluno.getEmail());

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> getAll() {
        List<AlunoResponseDTO> alunos = alunoRepository.findAll().stream().map(aluno -> {
            AlunoResponseDTO dto = new AlunoResponseDTO();
            dto.setId(aluno.getId());
            dto.setNome(aluno.getNome());
            dto.setEmail(aluno.getEmail());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> getById(@PathVariable Integer id) {
        return alunoRepository.findById(id)
                .map(aluno -> {
                    AlunoResponseDTO dto = new AlunoResponseDTO();
                    dto.setId(aluno.getId());
                    dto.setNome(aluno.getNome());
                    dto.setEmail(aluno.getEmail());
                    return ResponseEntity.ok(dto);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> update(@PathVariable Integer id, @RequestBody AlunoRequestDTO dto) {
        return alunoRepository.findById(id).map(aluno -> {
            aluno.setNome(dto.getNome());
            aluno.setEmail(dto.getEmail());
            Aluno updatedAluno = alunoRepository.save(aluno);

            AlunoResponseDTO responseDTO = new AlunoResponseDTO();
            responseDTO.setId(updatedAluno.getId());
            responseDTO.setNome(updatedAluno.getNome());
            responseDTO.setEmail(updatedAluno.getEmail());

            return ResponseEntity.ok(responseDTO);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

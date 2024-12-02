package com.Trabalho.educacional.controller;

import com.Trabalho.educacional.dto.ProfessorRequestDTO;
import com.Trabalho.educacional.dto.ProfessorResponseDTO;
import com.Trabalho.educacional.model.Professor;
import com.Trabalho.educacional.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    // Listar todos os professores
    @GetMapping
    public List<ProfessorResponseDTO> findAll() {
        return professorRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Buscar um professor pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> findById(@PathVariable Integer id) {
        return professorRepository.findById(id)
                .map(professor -> ResponseEntity.ok(convertToResponseDTO(professor)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar um novo professor
    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> save(@RequestBody ProfessorRequestDTO professorRequestDTO) {
        Professor professor = convertToEntity(professorRequestDTO);
        Professor savedProfessor = professorRepository.save(professor);
        return ResponseEntity.ok(convertToResponseDTO(savedProfessor));
    }

    // Atualizar um professor existente
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> update(@PathVariable Integer id, @RequestBody ProfessorRequestDTO professorRequestDTO) {
        return professorRepository.findById(id)
                .map(professor -> {
                    professor.setNome(professorRequestDTO.getNome());
                    professor.setEmail(professorRequestDTO.getEmail());
                    professor.setTelefone(professorRequestDTO.getTelefone());
                    professor.setEspecialidade(professorRequestDTO.getEspecialidade());
                    Professor updatedProfessor = professorRepository.save(professor);
                    return ResponseEntity.ok(convertToResponseDTO(updatedProfessor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar um professor pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Converter Professor para ProfessorResponseDTO
    private ProfessorResponseDTO convertToResponseDTO(Professor professor) {
        ProfessorResponseDTO responseDTO = new ProfessorResponseDTO();
        responseDTO.setId(professor.getId());
        responseDTO.setNome(professor.getNome());
        responseDTO.setEmail(professor.getEmail());
        responseDTO.setTelefone(professor.getTelefone());
        responseDTO.setEspecialidade(professor.getEspecialidade());
        return responseDTO;
    }

    // Converter ProfessorRequestDTO para Professor
    private Professor convertToEntity(ProfessorRequestDTO professorRequestDTO) {
        Professor professor = new Professor();
        professor.setNome(professorRequestDTO.getNome());
        professor.setEmail(professorRequestDTO.getEmail());
        professor.setTelefone(professorRequestDTO.getTelefone());
        professor.setEspecialidade(professorRequestDTO.getEspecialidade());
        return professor;
    }
}

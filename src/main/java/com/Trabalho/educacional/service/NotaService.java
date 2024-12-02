package com.Trabalho.educacional.service;

import com.Trabalho.educacional.model.Nota;
import com.Trabalho.educacional.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public List<Nota> getAllNotas() {
        return notaRepository.findAll();
    }

    public Nota getNotaById(Integer id) {
        Optional<Nota> nota = notaRepository.findById(id);
        return nota.orElse(null);
    }

    public Nota createNota(Nota nota) {
        return notaRepository.save(nota);
    }

    public Nota updateNota(Integer id, Nota notaDetails) {
        Nota nota = notaRepository.findById(id).orElseThrow(() -> new RuntimeException("Nota não encontrada"));
        // Corrigido o acesso ao campo "nota" diretamente
        nota.setNota(notaDetails.getNota()); // Utiliza o método setNota para atualizar a nota
        nota.setDisciplina(notaDetails.getDisciplina());
        nota.setMatricula(notaDetails.getMatricula());
        return notaRepository.save(nota);
    }

    public void deleteNota(Integer id) {
        Nota nota = notaRepository.findById(id).orElseThrow(() -> new RuntimeException("Nota não encontrada"));
        notaRepository.delete(nota);
    }
}

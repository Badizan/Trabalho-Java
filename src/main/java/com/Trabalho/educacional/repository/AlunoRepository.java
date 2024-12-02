package com.Trabalho.educacional.repository;

import com.Trabalho.educacional.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}

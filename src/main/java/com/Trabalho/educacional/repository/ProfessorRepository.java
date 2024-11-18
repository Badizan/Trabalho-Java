package com.Trabalho.educacional.repository;

import com.Trabalho.educacional.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}


package com.ufes.blog_aulas.repository;

import com.ufes.blog_aulas.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long>
{
}
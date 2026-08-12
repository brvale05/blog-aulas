package com.ufes.blog_aulas.repository;

import com.ufes.blog_aulas.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long>
{
}
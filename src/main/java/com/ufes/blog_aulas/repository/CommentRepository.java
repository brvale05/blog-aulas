package com.ufes.blog_aulas.repository;

import com.ufes.blog_aulas.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>
{
}
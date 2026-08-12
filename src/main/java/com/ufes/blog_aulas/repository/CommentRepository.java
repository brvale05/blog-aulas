package com.ufes.blog_aulas.repository;

import com.ufes.blog_aulas.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
{
    // Retorna apenas os comentários principais
    List<Comment> findByPostIdAndFatherCommentIsNull(Long postId);
}
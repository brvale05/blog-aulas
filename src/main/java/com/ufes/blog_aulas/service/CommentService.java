package com.ufes.blog_aulas.service;

import com.ufes.blog_aulas.domain.Comment;
import com.ufes.blog_aulas.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService
{
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository)
    {
        this.commentRepository = commentRepository;
    }

    public Comment getCommentEntityById(Long id)
    {
        return commentRepository.findById(id).orElseThrow(() -> new com.ufes.blog_aulas.exceptions.ResourceNotFoundException("Nenhum comentário com esse ID foi encontrado"));
    }

    public void save(Comment comment)
    {
        commentRepository.save(comment);
    }
}

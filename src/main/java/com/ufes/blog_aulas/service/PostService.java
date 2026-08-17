package com.ufes.blog_aulas.service;

import com.ufes.blog_aulas.domain.Comment;
import com.ufes.blog_aulas.domain.Post;
import com.ufes.blog_aulas.domain.Professor;
import com.ufes.blog_aulas.domain.Subject;
import com.ufes.blog_aulas.dto.request.CommentCreateDTO;
import com.ufes.blog_aulas.dto.request.PostCreateDTO;
import com.ufes.blog_aulas.dto.response.CommentResponseDTO;
import com.ufes.blog_aulas.dto.response.PostResponseDTO;
import com.ufes.blog_aulas.dto.response.ProfessorResponseDTO;
import com.ufes.blog_aulas.dto.response.SubjectResponseDTO;
import com.ufes.blog_aulas.exceptions.DataViolationException;
import com.ufes.blog_aulas.exceptions.ResourceNotFoundException;
import com.ufes.blog_aulas.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService
{
    private final PostRepository postRepository;

    private final ProfessorService professorService;
    private final SubjectService subjectService;
    private final CommentService commentService;

    public PostService(PostRepository postRepository, ProfessorService professorService, SubjectService subjectService, CommentService commentService)
    {
        this.postRepository = postRepository;
        this.professorService = professorService;
        this.subjectService = subjectService;
        this.commentService = commentService;
    }

    public PostResponseDTO save(PostCreateDTO createDTO)
    {
        Subject subject = subjectService.getSubjectEntityById(createDTO.subjectId());
        Professor professor = professorService.getProfessorEntityById(createDTO.professorId());

        Integer rating = createDTO.rating();

        if (rating > 5 || rating < 0)
            throw new DataViolationException("Avaliação da disciplina não pode ser maior que 5 ou menor que 0");

        Post post = new Post(subject, professor, rating, createDTO.content(), createDTO.difficulty());

        return toResponse(postRepository.save(post));
    }

    public PostResponseDTO addComment(CommentCreateDTO dto)
    {
        Post post = postRepository.findById(dto.postId())
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum post com esse ID foi encontrado"));

        Comment fatherComment = null;
        if (dto.fatherCommentId() != null)
        {
            fatherComment = commentService.getCommentEntityById(dto.fatherCommentId());
            if (!fatherComment.getPost().getId().equals(post.getId()))
            {
                throw new DataViolationException("O comentário pai não pertence a este post");
            }
        }

        Comment comment = new Comment(dto.text(), post, fatherComment);

        if (fatherComment == null)
        {
            post.addComment(comment);
        } else
        {
            fatherComment.addCommentResponse(comment);
        }

        commentService.save(comment);

        return toResponse(postRepository.save(post));
    }

    public PostResponseDTO getPostById(Long id)
    {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum post com esse ID foi encontrado"));

        return toResponse(post);
    }

    private PostResponseDTO toResponse(Post post)
    {
        ProfessorResponseDTO professorResponse = professorService.toResponse(post.getProfessor());
        SubjectResponseDTO subjectResponse = subjectService.toResponse(post.getSubject());

        // Busca apenas os comentários raiz e deixa os comentários filhos manipularem a própria hierarquia
        List<CommentResponseDTO> comments = post.getComments() != null ? 
                post.getComments().stream()
                        .filter(c -> c.getFatherComment() == null)
                        .map(this::mapCommentToResponseDTO).toList() : new ArrayList<>();

        return new PostResponseDTO(
                post.getId(),
                post.getContent(),
                post.getRating(),
                professorResponse,
                subjectResponse,
                comments,
                post.getCreatedAt(),
                post.getDifficulty()
        );
    }

    private CommentResponseDTO mapCommentToResponseDTO(Comment comment)
    {
        if (comment == null) return null;

        CommentResponseDTO father = null;
        // É comentário filho
        if (comment.getFatherComment() != null)
        {
            father = new CommentResponseDTO(
                    comment.getFatherComment().getId(),
                    comment.getFatherComment().getText(),
                    comment.getFatherComment().getCreatedAt(),
                    null, null, null);
        }

        List<CommentResponseDTO> children = comment.getChildrenComments() != null ?
                comment.getChildrenComments().stream().map(this::mapCommentToResponseDTO).toList() : new ArrayList<>();

        return new CommentResponseDTO(
                comment.getId(),
                comment.getText(),
                comment.getCreatedAt(),
                null,
                father,
                children
        );
    }
}

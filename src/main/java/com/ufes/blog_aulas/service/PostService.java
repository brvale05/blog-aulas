package com.ufes.blog_aulas.service;

import com.ufes.blog_aulas.domain.Post;
import com.ufes.blog_aulas.domain.Professor;
import com.ufes.blog_aulas.domain.Subject;
import com.ufes.blog_aulas.dto.request.PostCreateDTO;
import com.ufes.blog_aulas.dto.response.PostResponseDTO;
import com.ufes.blog_aulas.exceptions.DataViolationException;
import com.ufes.blog_aulas.exceptions.ResourceNotFoundException;
import com.ufes.blog_aulas.repository.PostRepository;
import com.ufes.blog_aulas.repository.ProfessorRepository;
import com.ufes.blog_aulas.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService
{
    private final PostRepository postRepository;
    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;

    public PostService(PostRepository postRepository, ProfessorRepository professorRepository, SubjectRepository subjectRepository)
    {
        this.postRepository = postRepository;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
    }

    public PostResponseDTO save(PostCreateDTO createDTO)
    {
        Subject subject = subjectRepository.findById(createDTO.subjectId()).orElseThrow(() -> new ResourceNotFoundException("Nenhuma matéria com esse ID foi encontrada"));
        Professor professor = professorRepository.findById(createDTO.professorId()).orElseThrow(()-> new ResourceNotFoundException("Nenhum docente com esse ID foi encontrado"));

        Integer rating = createDTO.rating();

        if (rating > 5 || rating < 0)
            throw  new DataViolationException("Avalição da disciplina não pode ser maior que 5 ou menor que 0");

        Post post = new Post(subject, professor, rating, createDTO.content());

        return null;
    }
}

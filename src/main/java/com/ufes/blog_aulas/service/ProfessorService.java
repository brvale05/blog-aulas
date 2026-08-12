package com.ufes.blog_aulas.service;

import com.ufes.blog_aulas.domain.Professor;
import com.ufes.blog_aulas.dto.request.ProfessorCreateDTO;
import com.ufes.blog_aulas.dto.response.ProfessorResponseDTO;
import com.ufes.blog_aulas.exceptions.ResourceAlreadyExistsException;
import com.ufes.blog_aulas.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService
{

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository)
    {
        this.professorRepository = professorRepository;
    }

    public ProfessorResponseDTO save(ProfessorCreateDTO createDTO)
    {
        if (professorRepository.existsByEmail(createDTO.email()))
            throw new ResourceAlreadyExistsException("Um docente com esse email já foi cadastrado");

        String name = createDTO.name().toUpperCase();

        Professor professor = new Professor(name, createDTO.email());

        return toResponse(professorRepository.save(professor));
    }

    private ProfessorResponseDTO toResponse(Professor professor)
    {
        return new ProfessorResponseDTO(
                professor.getId(),
                professor.getName(),
                professor.getEmail()
        );
    }
}

package com.ufes.blog_aulas.service;

import com.ufes.blog_aulas.domain.Subject;
import com.ufes.blog_aulas.dto.request.SubjectCreateDTO;
import com.ufes.blog_aulas.dto.response.SubjectResponseDTO;
import com.ufes.blog_aulas.exceptions.ResourceNotFoundException;
import com.ufes.blog_aulas.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectService
{
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository)
    {
        this.subjectRepository = subjectRepository;
    }

    public SubjectResponseDTO save(SubjectCreateDTO createDTO)
    {
        Subject subject = new Subject(createDTO.name());

        return toResponse(subjectRepository.save(subject));
    }

    public Subject getSubjectEntityById(Long id)
    {
        return subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhuma matéria com esse ID foi encontrada"));
    }

    public SubjectResponseDTO toResponse(Subject subject)
    {
        return new SubjectResponseDTO(
                subject.getId(),
                subject.getName()
        );
    }
}

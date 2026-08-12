package com.ufes.blog_aulas.service;

import com.ufes.blog_aulas.domain.Subject;
import com.ufes.blog_aulas.dto.request.SubjectCreateDTO;
import com.ufes.blog_aulas.dto.response.SubjectResponseDTO;
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

    private SubjectResponseDTO toResponse(Subject subject)
    {
        return new SubjectResponseDTO(
                subject.getId(),
                subject.getName()
        );
    }
}

package com.ufes.blog_aulas.controller;

import com.ufes.blog_aulas.dto.request.SubjectCreateDTO;
import com.ufes.blog_aulas.dto.response.SubjectResponseDTO;
import com.ufes.blog_aulas.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
public class SubjectController
{
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService)
    {
        this.subjectService = subjectService;
    }

    @PostMapping("/save")
    public ResponseEntity<SubjectResponseDTO> save(@RequestBody SubjectCreateDTO createDTO)
    {
        SubjectResponseDTO responseDTO = subjectService.save(createDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}

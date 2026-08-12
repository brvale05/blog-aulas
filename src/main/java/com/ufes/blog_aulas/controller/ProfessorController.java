package com.ufes.blog_aulas.controller;

import com.ufes.blog_aulas.dto.request.ProfessorCreateDTO;
import com.ufes.blog_aulas.dto.response.ProfessorResponseDTO;
import com.ufes.blog_aulas.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professor")
public class ProfessorController
{
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService)
    {
        this.professorService = professorService;
    }

    @PostMapping("/save")
    public ResponseEntity<ProfessorResponseDTO> save(@RequestBody ProfessorCreateDTO createDTO)
    {
        ProfessorResponseDTO responseDTO = professorService.save(createDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}

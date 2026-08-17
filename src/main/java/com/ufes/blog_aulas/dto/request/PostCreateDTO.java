package com.ufes.blog_aulas.dto.request;

import com.ufes.blog_aulas.enums.SubjectDifficulty;

import java.io.Serializable;

/**
 * DTO for {@link com.ufes.blog_aulas.domain.Post}
 */
public record PostCreateDTO(

        String content,

        Integer rating,

        Long professorId,

        Long subjectId,

        SubjectDifficulty difficulty

) implements Serializable
{
}
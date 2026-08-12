package com.ufes.blog_aulas.dto.response;

import java.io.Serializable;

/**
 * DTO for {@link com.ufes.blog_aulas.domain.Professor}
 */
public record ProfessorResponseDTO(

        Long id,

        String name,

        String email

) implements Serializable
{
}
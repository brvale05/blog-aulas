package com.ufes.blog_aulas.dto.response;

import java.io.Serializable;

/**
 * DTO for {@link com.ufes.blog_aulas.domain.Subject}
 */
public record SubjectResponseDTO(

        Long id,

        String name

) implements Serializable
{
}
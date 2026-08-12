package com.ufes.blog_aulas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.ufes.blog_aulas.domain.Subject}
 */
public record SubjectCreateDTO(

        @NotBlank
        @Size(max = 80)
        String name

) implements Serializable
{
}
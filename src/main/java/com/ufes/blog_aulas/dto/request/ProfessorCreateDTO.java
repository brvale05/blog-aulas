package com.ufes.blog_aulas.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.ufes.blog_aulas.domain.Professor}
 */
public record ProfessorCreateDTO(

        @NotBlank
        @Size(max = 80)
        String name,

        @Email
        @NotBlank
        @Size(max = 80)
        String email

) implements Serializable
{
}
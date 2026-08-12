package com.ufes.blog_aulas.dto.request;

import java.io.Serializable;

/**
 * DTO for {@link com.ufes.blog_aulas.domain.Subject}
 */
public record SubjectCreateDTO(String name) implements Serializable
{
}
package com.ufes.blog_aulas.dto.request;

import java.io.Serializable;

/**
 * DTO for {@link com.ufes.blog_aulas.domain.Comment}
 */
public record CommentCreateDTO(

        String text,

        Long postId,

        Long fatherCommentId

) implements Serializable
{
}
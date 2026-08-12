package com.ufes.blog_aulas.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record CommentResponseDTO(

        Long id,

        String text,

        LocalDateTime createdAt,

        PostResponseDTO post,

        CommentResponseDTO fatherComment,

        List<CommentResponseDTO> childrenComments
)
{
}

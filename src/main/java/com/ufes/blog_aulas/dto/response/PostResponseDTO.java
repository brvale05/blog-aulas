package com.ufes.blog_aulas.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponseDTO(

        Long id,

        String content,

        Integer rating,

        ProfessorResponseDTO professor,

        SubjectResponseDTO subject,

        List<CommentResponseDTO> comments,

        LocalDateTime createdAt

)
{
}

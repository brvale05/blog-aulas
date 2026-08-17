package com.ufes.blog_aulas.controller;

import com.ufes.blog_aulas.dto.request.CommentCreateDTO;
import com.ufes.blog_aulas.dto.request.PostCreateDTO;
import com.ufes.blog_aulas.dto.response.PostResponseDTO;
import com.ufes.blog_aulas.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController
{
    private final PostService postService;

    public PostController(PostService postService)
    {
        this.postService = postService;
    }

    @PostMapping("/save")
    public ResponseEntity<PostResponseDTO> save(@RequestBody PostCreateDTO createDTO)
    {
        PostResponseDTO responseDTO = postService.save(createDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("/addComment")
    public ResponseEntity<PostResponseDTO> addComment(@RequestBody CommentCreateDTO comment)
    {
        PostResponseDTO postResponseDTO = postService.addComment(comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDTO);
    }
}

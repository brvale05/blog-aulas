package com.ufes.blog_aulas.repository;

import com.ufes.blog_aulas.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>
{
}
package com.ufes.blog_aulas.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_comment")
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String text;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // Será NULL se estiver respondendo o post, no caso, não possui comentário pai
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "father_comment_id")
    private Comment fatherComment;

    @OneToMany(mappedBy = "fatherComment")
    private List<Comment> childrenComments;

    // Colocar um USER aqui

    public Comment(String text, Post post, Comment fatherComment)
    {
        this.text = text;
        this.post = post;
        this.createdAt = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        this.fatherComment = fatherComment;
        this.childrenComments = new ArrayList<>();
    }

    public Comment()
    {
    }

    public Long getId()
    {
        return id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public Post getPost()
    {
        return post;
    }

    public void setPost(Post post)
    {
        this.post = post;
    }

    public Comment getFatherComment()
    {
        return fatherComment;
    }

    public void setFatherComment(Comment fatherComment)
    {
        this.fatherComment = fatherComment;
    }

    public List<Comment> getChildrenComments()
    {
        return childrenComments;
    }

    public void setChildrenComments(List<Comment> commentResponses)
    {
        this.childrenComments = commentResponses;
    }

    public void addCommentResponse(Comment response)
    {
        if (response != null)
        {
            this.childrenComments.add(response);
            response.setFatherComment(this);
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Comment comment)) return false;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(id);
    }

}

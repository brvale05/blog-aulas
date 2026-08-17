package com.ufes.blog_aulas.domain;

import com.ufes.blog_aulas.enums.SubjectDifficulty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_post")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private SubjectDifficulty difficulty;

    public Post(Subject subject, Professor professor, Integer rating, String content, SubjectDifficulty difficulty)
    {
        this.createdAt = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        this.comments = new ArrayList<>();
        this.subject = subject;
        this.professor = professor;
        this.rating = rating;
        this.content = content;
        this.difficulty = difficulty;
    }

    public Post()
    {
    }

    public Long getId()
    {
        return id;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Integer getRating()
    {
        return rating;
    }

    public void setRating(Integer rating)
    {
        this.rating = rating;
    }

    public Professor getProfessor()
    {
        return professor;
    }

    public void setProfessor(Professor professor)
    {
        this.professor = professor;
    }

    public Subject getSubject()
    {
        return subject;
    }

    public void setSubject(Subject subject)
    {
        this.subject = subject;
    }

    public List<Comment> getComments()
    {
        return comments;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public SubjectDifficulty getDifficulty()
    {
        return difficulty;
    }

    public void setDifficulty(SubjectDifficulty difficulty)
    {
        this.difficulty = difficulty;
    }

    public void addComment(Comment c)
    {
        if (c != null)
        {
            this.comments.add(c);
            c.setPost(this);
        }
    }

    public void removeComment(Comment c)
    {
        if (c != null)
        {
            for (Comment children : c.getChildrenComments())
            {
                if (children != null)
                    children.setFatherComment(null);
            }

            this.comments.remove(c);
            c.setPost(null);
        }
    }
}

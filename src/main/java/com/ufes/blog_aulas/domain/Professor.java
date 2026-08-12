package com.ufes.blog_aulas.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_professor")
public class Professor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, unique = true, length = 80)
    private String email;

    public Professor(String name, String email)
    {
        this.name = name;
        this.email = email;
    }

    public Professor()
    {
    }

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}

package com.ufes.blog_aulas.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_subject")
public class Subject
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    public Subject(String name)
    {
        this.name = name;
    }

    public Subject()
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
}

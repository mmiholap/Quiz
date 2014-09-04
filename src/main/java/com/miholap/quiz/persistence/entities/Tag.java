package com.miholap.quiz.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=60)
    private String title;

    @ManyToMany(mappedBy = "tags")
    private Collection<Quiz> quizs;

    public Tag(String title) {
        this.title = title;
    }

    public Tag() {
    }

    public Collection<Quiz> getQuizs() {
        return quizs;
    }

    public void setQuizs(Collection<Quiz> quizs) {
        this.quizs = quizs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

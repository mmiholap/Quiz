package com.miholap.quiz.persistence.entities;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Table(name = "quiz")
@Entity
public class Quiz implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    @OneToMany(mappedBy = "quiz")
    private Collection<Question> questions;

    public Quiz() {
    }

    public Quiz(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }
}

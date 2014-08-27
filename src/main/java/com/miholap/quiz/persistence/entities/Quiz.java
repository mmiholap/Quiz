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

    private String title;

    private String description;

    @OneToMany(mappedBy = "quiz",fetch = FetchType.EAGER)
    private Collection<Question> questions;

    @ManyToMany
    @JoinTable(
            name="Catalog",
            joinColumns = {@JoinColumn(name = "quiz_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id",referencedColumnName = "id")})
    private Collection<Tag> tags;

    public Quiz() {
    }

    public Quiz(String title, String description) {
        this.description = description;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz quiz = (Quiz) o;

        if (id != quiz.id) return false;
        if (description != null ? !description.equals(quiz.description) : quiz.description != null) return false;
        if (!title.equals(quiz.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + title.hashCode();
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
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

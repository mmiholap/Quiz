package com.miholap.quiz.persistence.entities;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries(
        @NamedQuery(name="Answer.selectActiveAnswersForQuiz",
                query="SELECT a from Answer a where a.question.id = :question_id AND a.isActive = true")
)

public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @Column(name = "is_right")
    private boolean isRight = false;
    @Column(name = "is_active")
    private boolean isActive = true;

    public Answer() {
    }

    public Answer(String text, Question question) {
        this.text = text;
        this.question = question;
    }

    public Answer(String text, Question question, boolean isRight) {
        this.text = text;
        this.question = question;
        this.isRight = isRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (id != answer.id) return false;
        if (isActive != answer.isActive) return false;
        if (isRight != answer.isRight) return false;
        if (!question.equals(answer.question)) return false;
        if (!text.equals(answer.text)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + text.hashCode();
        result = 31 * result + question.hashCode();
        result = 31 * result + (isRight ? 1 : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", question=" + question.getId() +
                ", isRight=" + isRight +
                ", isActive=" + isActive +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean isRight) {
        this.isRight = isRight;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}

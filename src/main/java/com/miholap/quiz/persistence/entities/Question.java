package com.miholap.quiz.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;

@Table(name="question")
@Entity
@NamedQueries({
        @NamedQuery(name="Question.activeQuestions",
                query = "SELECT q FROM Question q WHERE q.quiz.id = :quiz_id AND q.isActive = true")
})
public class Question implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=1000)
    private String text;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name="quiz_id")
    private Quiz quiz;

    @Column(name = "answer_time")
    private int answerTime=30;

    @Column(name="is_active")
    private boolean isActive=true;

    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER)
    private Collection<Answer> answers;

    public Question() {
    }

    public Question(String text, Quiz quiz) {
        this.text = text;
        this.quiz = quiz;
    }

    public Question(String text, String imagePath, Quiz quiz) {
        this.text = text;
        this.imagePath = imagePath;
        this.quiz = quiz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (answerTime != question.answerTime) return false;
        if (id != question.id) return false;
        if (isActive != question.isActive) return false;
        if (!quiz.equals(question.quiz)) return false;
        if (!text.equals(question.text)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + text.hashCode();
        result = 31 * result + quiz.hashCode();
        result = 31 * result + answerTime;
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", quiz=" + quiz.getId() +
                ", answerTime=" + answerTime +
                ", isActive=" + isActive +
                '}';
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
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

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(int answerTime) {
        this.answerTime = answerTime;
    }
}

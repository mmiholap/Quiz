package com.miholap.quiz.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
public class Statistics implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "right_answers")
    private int rightAnswers;

    @Column(name = "questions")
    private int questions;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar time;

    public Statistics() {
    }

    public Statistics(Quiz quiz, User user, int rightAnswers, int questions, Calendar time) {
        this.quiz = quiz;
        this.user = user;
        this.rightAnswers = rightAnswers;
        this.questions = questions;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistics that = (Statistics) o;

        if (id != that.id) return false;
        if (questions != that.questions) return false;
        if (rightAnswers != that.rightAnswers) return false;
        if (!quiz.equals(that.quiz)) return false;
        if (!time.equals(that.time)) return false;
        if (!user.equals(that.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + quiz.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + rightAnswers;
        result = 31 * result + questions;
        result = 31 * result + time.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", quiz=" + quiz +
                ", user=" + user +
                ", rightAnswers=" + rightAnswers +
                ", questions=" + questions +
                ", time=" + time +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }
}

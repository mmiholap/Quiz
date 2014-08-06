package com.miholap.quiz.entities;

import javax.persistence.*;

@Table(name="users")
@Entity
public class User {
    public static final int USER = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private int role;
    private String fullname;

    public User() {};

    public User(String login, String password, int role, String fullname) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString(){
        return "id:"+id+" login:"+login+" fullname:"+fullname+" password:"+password;
    }
}

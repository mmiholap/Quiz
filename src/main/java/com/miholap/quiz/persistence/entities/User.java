package com.miholap.quiz.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="users")
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String fullname;

    public User() {};

    public User(String login, String password, Role role, String fullname) {
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
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
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
        return "id:"+id+" login:"+login+" fullname:"+fullname+" password:"+password+" role:"+role;
    }
}

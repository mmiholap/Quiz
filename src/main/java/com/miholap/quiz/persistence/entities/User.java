package com.miholap.quiz.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Table(name="user")
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

    @OneToMany(mappedBy = "user")
    private Collection<Statistics> statisticses;

    public User() {};

    public User(String login, String password, Role role, String fullname) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!fullname.equals(user.fullname)) return false;
        if (!login.equals(user.login)) return false;
        if (role != user.role) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + login.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + fullname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", fullname='" + fullname + '\'' +
                ", statisticses=" + statisticses +
                '}';
    }

    public Collection<Statistics> getStatisticses() {
        return statisticses;
    }

    public void setStatisticses(Collection<Statistics> statisticses) {
        this.statisticses = statisticses;
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

}

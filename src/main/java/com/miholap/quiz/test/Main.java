package com.miholap.quiz.test;

import com.miholap.quiz.persistence.entities.Role;
import com.miholap.quiz.persistence.entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


public class Main {
    public static void main(String[] args) {
        User user = new User("test","test", Role.USER,"qwerty");
        ApplicationContext cntx =new ClassPathXmlApplicationContext("spring/application-config.xml");
        EntityManagerFactory emf = cntx.getBean(EntityManagerFactory.class);
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        TypedQuery<User> query = entityManager.createQuery("select usr from User usr where usr.id = 1", User.class);
        User testUser  = query.getSingleResult();
        System.out.println(testUser.toString());
    }
}

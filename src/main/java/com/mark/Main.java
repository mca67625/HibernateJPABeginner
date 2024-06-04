package com.mark;

import com.mark.controller.UserController;
import com.mark.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        UserController userController = new UserController();

        //Before you Run the method, findUser() and addUser().
        // Comment out the method addUser().

        //Testing Guided Lab 305.4.1B Hibernate Project Demonstration with IntelliJ Ultimate

        //userController.addUser(session);
        //userController.findUser(session,1);
        //userController.updateUser(session,1);
        //userController.deleteUser(session,1);

        //Testing Guided LAB 305.4.2B - Demonstration of Hibernate Query Language - HQL with Intellij

        //userController.findUserHql(session);
        //userController.getRecordById(session);
        //userController.getRecords(session);
        //userController.getMaxSalary(session);
        //userController.getMaxSalaryGroupBy(session);
        userController.nameQueryExample(session);

        session.close();
        sessionFactory.close();

    }
}
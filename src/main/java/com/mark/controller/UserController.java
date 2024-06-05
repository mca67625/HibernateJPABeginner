package com.mark.controller;

import com.mark.model.User;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.List;

public class UserController  {



   public void addUser(Session session){
       Transaction tx = session.beginTransaction();

       User user1 = new User();
       user1.setEmail("haseeb@gmail.com");
       user1.setFullName("Moh Haseeb");
       user1.setPassword("has123");
       user1.setSalary(2000.69);
       user1.setAge(20);
       user1.setCity("NYC");
       session.persist(user1);

       User user2 = new User();
       user2.setEmail("James@gmail.com");
       user2.setFullName("James Santana");
       user2.setPassword("James123");
       user2.setSalary(2060.69);
       user2.setAge(25);
       user2.setCity("Dallas");
       session.persist(user2);

       User user3 = new User();
       user3.setEmail("Shahparan@gmail.com");
       user3.setFullName("Shah Paran");
       user3.setPassword("Shah123");
       user3.setSalary(3060.69);
       user3.setAge(30);
       user3.setCity("Chicago");
       session.persist(user3);

       /*===We can pass user values/data by using constructor ==**/
       User user4 = new User("Chris", "Chris@gmail.com", "147852", 32, 35000.3, "NJ");
       session.persist(user4);

       tx.commit();

       System.out.println("Successfully added users to database");
   }

    public void findUser(Session session, int userId){
         Transaction tx = session.beginTransaction();

         //The session.get(Class, id) returns an object of the specified
        // class that maps a row in the database table. If no row is found,
        // it returns null

         User user = (User) session.get(User.class, userId);
         System.out.println( "FullName: "+ user.getFullName());
         System.out.println( "Email: "+ user.getEmail());
         System.out.println( "Password: "+ user.getPassword());

         tx.commit();
    }

    public void updateUser(Session session,int userId){
       Transaction tx = session.beginTransaction();
       User user = new User();
       user.setId(userId);
       user.setEmail("mhaseeb@perscholas.com");
       user.setFullName("Mohan Haseeb");
       user.setPassword("123456");
       session.update(user); //both the use of update or merge removes the previous data that has not been set with the set methods
       //session.merge(user); //only currently set data will be stored in the record previous data will be deleted
       session.getTransaction().commit();
    }

    public void deleteUser(Session session,int userId){
       Transaction tx = session.beginTransaction();
       User user = (User) session.get(User.class, userId);
       session.remove(user);
       tx.commit();
    }

    //GLAB 305.4.2 content created below

    public void findUserHql(Session session) {

        //Example of HQL to get all records from user class

        String hqlFrom = "FROM User";
        String hqlSelect = "Select u FROM User u";
        TypedQuery<User> query = session.createQuery(hqlSelect, User.class);
        List<User> resultList = query.getResultList();
        System.out.printf("%s%13s%17s%34s%n", "| User Id", "| Full name", "| Email", "| Password");
        for (User user : resultList) {
            System.out.printf("%-10d %-20s %-30s %s %n", user.getId(), user.getFullName(), user.getEmail(), user.getPassword());
        }
    }

    public void getRecordById(Session session){
       String hql = "FROM User u WHERE u.id > 2 ORDER BY u.salary DESC";
       TypedQuery <User> query = session.createQuery(hql, User.class);
       List<User> resultList = query.getResultList();
        System.out.printf("%s%13s%17s%34s%21s%n", "| User Id", "| Full name", "| Email", "| Password", "| Salary");
       for (User user : resultList) {
           System.out.printf("%-10d %-20s %-30s %-23s %s %n", user.getId(), user.getFullName(), user.getEmail(), user.getPassword(), user.getSalary());
       }
   }
    //This method, we will use the multiple columns as shown below:

    public void getRecords(Session session){
       TypedQuery<Object[]> query = session.createQuery("select u.salary, u.fullName from User as u", Object[].class);
       List<Object[]> resultList = query.getResultList();
        System.out.printf("%s %18s %n", "Salary", "| Full Name");
        for (Object[] objects : resultList) {
            System.out.printf("%-16s%s%n", objects[0], objects[1]);
        }
    }

    //Aggregate functions: In this method, we will use the “max()” function as shown below:
    //Note: We will use the getSingleResult() method. This method executes a SELECT query that
    // returns a single untyped result.

    public void getMaxSalary(Session session){
       String hql = "SELECT MAX(salary) FROM User";
       TypedQuery <Object> query = session.createQuery(hql, Object.class);
       Object result = query.getSingleResult();
       System.out.printf("%s %s %n", "Maximum Salary", result);
    }

    //Group By Clause and Aggregate function. In this method, we will use the Group By clause
    public void getMaxSalaryGroupBy(Session session){
       String hql = "SELECT Sum(u.salary), u.city FROM User u GROUP BY u.city";
       TypedQuery query = session.createQuery(hql);
       List<Object[]> resultList = query.getResultList();
       for (Object[] objects : resultList) {
           System.out.println("Total Salary: "+objects[0] + " | City: " + objects[1]);
       }
    }

   // Using Named Parameters Syntax
   // Hibernate supports named parameters in its HQL queries. This makes writing HQL queries that accept input from the
   // user easy, and the user does not have to defend against SQL injection attacks. Following is the syntax of using
   // named parameters:

    public void nameQueryExample(Session session){
       String hql = "FROM User u WHERE u.id = :id";
       TypedQuery <User> query = session.createQuery(hql, User.class);
       query.setParameter("id", 2);
       List<User> resultList = query.getResultList();
        System.out.printf("%s %13s %17s %30s %21s %n", "|User Id", "|Full name", "|Email", "|Password", "|Salary");
       for (User user : resultList) {
           System.out.printf("%-10d %-20s %-30s %-23s %s %n", user.getId(), user.getFullName(), user.getEmail(), user.getPassword(), user.getSalary());
       }
    }

}


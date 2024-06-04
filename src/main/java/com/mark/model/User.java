package com.mark.model;
import jakarta.persistence.*;

/**
 * ●	@Entity annotation specifies that the class is an entity.
 * ●	@Table maps the entity with the table. If no @Table is defined, the default value is used:
 *      the class name of the entity.
 * ●	@Id declares the identifier property of the entity.
 * ●	@Column maps the entity's field to the table's column. If @Column is omitted, the default value is used:
 *      the field name of the entity.
 */

@Entity
@Table(name = "USER")
public class User {
    @Column(name = "USER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String email;
    private String password;
    private int age;
    private double salary;
    private String city;

    public User() {}

    public User(String fullName, String email, String password, int age, double salary, String city) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.salary = salary;
        this.city = city;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", city='" + city + '\'' +
                '}';
    }


}

package com.example.demo.Student;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.time.Period;
import javax.persistence.*;

/*
    @Entity annotation marks that the class is mapped to a table
    @Table annotation is used to denote the tables name

    Entity is more to specify that it should be mapped to a database, table is for naming

    @id used for making primary key of table imported from javax.persistence.Id
    @SequenceGenerator is a primary key generator
        name: name of the generator to be referenced
        sequenceName: name of the database object to generate keys from
        allocationSize: The amount to increment
     @GeneratedValue Provides the strategy to generate primary keys
        strategy - The strategy used to generate
            Multiple GenerationTypes
                - TABLE - generate IDs from another db
                - SEQUENCE - use a database sequence
                - IDENTITY - generated ids from another column
                - AUTO - auto pick the best way to generate
        generator - the name of the generator

 */
@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;

    public Student(){
    }
    public Student(Long id, String name, String email, LocalDate dob){
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name, String email, LocalDate dob){
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public LocalDate getDob(){
        return this.dob;
    }

    public void setDob(LocalDate dob){
        this.dob = dob;
    }

    public Integer getAge(){
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age){
        this.age = age;
    }

    @Override
    public String toString(){
        return "Student {" +
                    "id: " + this.id +
                    "name: " + this.name +
                    "email: " + this.email +
                    "dob: " + this.dob +
                    "age: " + this.age +
                "}";
    }


}

package com.example.demo.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.JUNE;

/*

The IoC (inversion of control) Container is where we manage all our objects instead of creating new objects in our spring app
An object can retrieve data from the IoC container

@Configuration annontation means that the class will contain bean methods in the IoC container that may be used within our application
@Bean methods return a bean to the IoC container to be used within the spring app

CommandLineRunner will run after the spring appplication has started
 */

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository sr){
        return args -> {
            Student justin = new Student(
                    "justin",
                    "justin@gmail.com",
                    LocalDate.of(2000, JUNE,4)
            );
            Student bill = new Student(
                    "bill",
                    "bill@gmail.com",
                    LocalDate.of(2004, JUNE,5)
            );

            sr.saveAll(
                    List.of(justin, bill)
            );
        };
    }
}

package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

/*
    service layer @service will just tell spring that it is a service component
 */
@Service
public class StudentService {
    private final StudentRepository sr;

    @Autowired
    public StudentService(StudentRepository sr){
        this.sr = sr;
    }
    public List<Student> getStudents(){
        return sr.findAll();
    }

    public void addNewStudent(Student student){
        Optional<Student> studentByEmail = sr.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email taken");
        }

        sr.save(student);
    }

    public void deleteStudent(Long id){
        boolean exists = sr.existsById(id);
        if(!exists) throw new IllegalStateException("Student " +  id + " not found");
        sr.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email){
        boolean exists = sr.existsById(id);
        if(!exists) throw new IllegalStateException("Student " +  id + " not found");
        Student current = sr.getReferenceById(id);

        if(name != null && name.length() > 0 && !name.equals(current.getName())){
            current.setName(name);
        }
        if(email != null && email != "" && !email.equals(current.getEmail())){
            Optional<Student> studentOptional = sr.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email exists");
            }
            current.setEmail(email);
        }
    }
}

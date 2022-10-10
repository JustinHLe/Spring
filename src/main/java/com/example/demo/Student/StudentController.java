package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/*
    The controller is responsible for processing any requests and returning responses

    The RestController is made up of two other annontations
        @Component - designates a spring component
            - However with RestController it will also mark the class as the business/presentation layer
            - When a request is made it will inform the DispatcherServlet to look for methods in the controller class designated by the mapping URL
                - DispatcherServlet is a front controller meaning all requests are singlehandedly processed by the dispatcherServlet
                - DispatcherServlet will then decide which controller should process the request
        @ResponseBody - turn data to JSON before sending to client
 */

@RestController // Indicates that the student controller serves the role as a controller
@RequestMapping(path = "api/v1/student") // used to map an URL to a class
public class StudentController {
    private final StudentService ss;

    /*
        Autowired will inject dependencies of a type, in this case StudentService is injected without initializing a new StudentService
        no need to instantiate a new studentService with autowired
     */
    @Autowired
    public StudentController(StudentService ss){
        this.ss = ss;
    }

    @GetMapping //Map GET requests onto handler method everytime we get from the mappingURL we return this method
    public List<Student> getStudents(){
        return this.ss.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        ss.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        ss.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String email){
        ss.updateStudent(id, name, email);
    }
}

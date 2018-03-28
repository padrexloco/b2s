package com.nikrob.controller;

import com.nikrob.model.Student;
import com.nikrob.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/*
 *To be able to access the student's services,
 * the controller offloads the requests to student services
 */
@RestController
@RequestMapping("/students") //a partFor dependency injection => this allows decoupling system: I don't have to say new...
public class StudentController {

    @Autowired // find a bean, instanciate and inject it here.
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    //@Requestmapping(value = "/", method = RequestMethod.Get) //  I could add value, but I don't need it now
    public Collection<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable("id") int id) {//to gather the id from the url

        return studentService.getStudentById(id);
    }

    @RequestMapping(value = "{/id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable("id") int id){

        studentService.removeStudentId(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE) //To avoid Unsupported MediaType, so tell Spring we feet it json
    public void updateStudent(@RequestBody Student student){ //@RequestBody to avoid NoPointerException

        this.studentService.updateStudent(student);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudent(@RequestBody Student student){

        studentService.insertStudentId(student);
    }

}

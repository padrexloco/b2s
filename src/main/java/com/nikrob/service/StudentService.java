package com.nikrob.service;


import com.nikrob.dao.StudentDao;
import com.nikrob.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

/*
 *To use the database (Dao)
 */
@Service
public class StudentService {

    /*
     *An instance to access Dao
     */
    @Autowired
    @Qualifier("mysql") //since we have more than one class that implement StudentDao interface.
    // to allow spring to know which one to connect with
    private StudentDao studentDao; //using interface object that access db or fake data structure.

    public Collection<Student> getAllStudents() { return this.studentDao.getAllStudents(); }

    public Student getStudentById(int id) { return this.studentDao.getStudentById(id); }

    public void removeStudentId(int id) {
        this.studentDao.removeStudentById(id);
    }

    public void updateStudent(Student student){ this.studentDao.updateStudent(student); }

    public void insertStudentId(Student student) { this.studentDao.insertStudentToDB(student); }
}

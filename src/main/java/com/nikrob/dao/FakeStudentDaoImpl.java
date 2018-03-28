package com.nikrob.dao;

import com.nikrob.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*
 * Dao in general will give us the possibility to connect to our database
 * But now, we are going to work with fake data (hard coded)
 */
@Repository //spring instanciate a bean for me
@Qualifier("fakeData") //To identify itself to student services
public class FakeStudentDaoImpl implements StudentDao {

    @Autowired
    private static Map<Integer, Student> students;

    static {

        students = new HashMap<Integer, Student>(){
            {
                put(1, new Student(1, "Sak", "Madik", 4));
                put(2, new Student(2, "Qi", "Smaas", 2));
            }
        };
    }

    /*
     *Provide a way to get info from data
     */

    @Override
    public Collection<Student> getAllStudents(){
        return this.students.values();
    }

    @Override
    public Student getStudentById(int id){
        return this.students.get(id);
    }

    @Override
    public void removeStudentById(int id) {

        this.students.remove(id);
    }

    @Override
    public void updateStudent(Student student){

        Student s = students.get(student.getStudentID());
        s.setStandard(student.getStandard());
        s.setFirstName(student.getFirstName());
        s.setLastName(student.getLastName());
        students.put(student.getStudentID(), student); //put back the student in dao
    }

    @Override
    public void insertStudentToDB(Student student) {

        this.students.put(student.getStudentID(), student);
    }
}

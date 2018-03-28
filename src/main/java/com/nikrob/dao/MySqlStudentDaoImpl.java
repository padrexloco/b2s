package com.nikrob.dao;

import com.nikrob.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository("mysql")//give it a name
public class MySqlStudentDaoImpl implements StudentDao {

    //private JdbcTemplate jdbcTemplate = new JdbcTemplate("datasource""); //it was supposed to be like this
    //but since we have our connection in .properties, no need, instead, we do the following...

    @Autowired //Which creates the bean and injects the datasource for us
    private JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Student> getAllStudents() {
        //SELECT column_name(s) FROM table_name
        jdbcTemplate.query("SELECT id, lastName, firstName, standard FROM students", new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                student.setStudentID(resultSet.getInt("id"));
                return null;
            }
        });
        return null;
    }

    @Override
    public Student getStudentById(int id) {
        return null;
    }

    @Override
    public void removeStudentById(int id) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void insertStudentToDB(Student student) {

    }
}

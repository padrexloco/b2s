package com.nikrob.dao;

import com.nikrob.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository("mysql")//give it a name
public class MySqlStudentDaoImpl implements StudentDao {


    //private JdbcTemplate jdbcTemplate = new JdbcTemplate("datasource""); //it was supposed to be like this
    //but since we have our connection in .properties, no need, instead, we do the following...

    @Autowired //Which creates the bean and injects the data source for us
    private JdbcTemplate jdbcTemplate;

    private static class StudentRowMapper implements RowMapper<Student>{//class created to avoid code duplicate error

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();
            student.setStudentID(resultSet.getInt("id")); //column index or col label
            student.setFirstName(resultSet.getString("lastName"));
            student.setLastName(resultSet.getString("firstName"));
            student.setStandard(resultSet.getInt("standard"));
            return student;
        }
    }

    @Override
    public Collection<Student> getAllStudents() {
        //SELECT column_name(s) FROM table_name
        final String sql = "SELECT id, lastName, firstName, standard FROM students";
        List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());

        return students;
    }

    @Override
    public Student getStudentById(int id) {
        //SELECT column_name(s) FROM table_name where column = value
        final String sql = "SELECT id, lastName, firstName, standard FROM students where id = ?";//the param "id" will be replaced by the ? and this will avoid the sql injection
        Student student = jdbcTemplate.queryForObject(sql, new StudentRowMapper(), id);
        return student;
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

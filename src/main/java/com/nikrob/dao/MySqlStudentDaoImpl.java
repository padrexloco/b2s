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
        //DELETE FROM table_name
        //WHERE some_column = some_value
        final String sql = "DELETE  FROM students WHERE id = ?";
        jdbcTemplate.update(sql, id); //update is used for everything except GET

    }

    @Override
    public void updateStudent(Student student) {
        //UPDATE table_name
        //SET column1=value, column2=value2,...
        //WHERE some_column=some_value
        final int id = student.getStudentID();
        final String sql = "UPDATE students SET lastName = ?, firstName = ?, standard = ?";
        final String lastName = student.getLastName();
        final String firstName = student.getFirstName();
        final int standard = student.getStandard();

        jdbcTemplate.update(sql, new Object[]{lastName, firstName, standard, id});
    }

    @Override
    public void insertStudentToDB(Student student) {
        //INSERT INTO table_name (column1, column2, column3,...)
        //VALUES (value1, value2, value3,...)
        final String sql = "INSERT INTO students (lastName, firstName, standard) VALUES (?,?,?)";
        final int id = student.getStudentID();
        final String lastName = student.getLastName();
        final String firstName = student.getFirstName();
        final int standard = student.getStandard();
        jdbcTemplate.update(sql, new Object[] {lastName, firstName, standard});
    }
}

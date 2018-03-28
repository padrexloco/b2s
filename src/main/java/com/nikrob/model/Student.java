package com.nikrob.model;

/**
 * @author Nik & Rob
 *
 */

public class Student {

    private int studentID;
    private String lastName;
    private String firstName;
    private int standard; 		//class



    public Student(int studentID, String lastName, String firstName, int standard) {
        this.studentID = studentID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.standard = standard;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

}


package project.controllers;

import project.DAO.StudentCourseDAO;

public class StudentCourseController {

    private StudentCourseDAO studentCourseDAO = new StudentCourseDAO();

    public void registerCourse(int studentID, String coursecode) {
        studentCourseDAO.registerCourse(studentID, coursecode);
    }

    public java.util.List<String> getCoursesForStudent(int studentID) {
        return studentCourseDAO.getCoursesForStudent(studentID);
    }
}

package project.controllers;

import project.DAO.CourseDAO;
import project.model.Course;
import java.util.*;

public class CourseController {

    private CourseDAO courseDAO = new CourseDAO();

    public void addCourse(String code, String name) {
        Course course = new Course(code, name, null);
        courseDAO.saveCourse(course);
    }

    public List<Course>  getAllCourses() {
        return courseDAO.getAllCourses();
    }
}

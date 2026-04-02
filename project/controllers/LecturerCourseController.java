package project.controllers;

import project.DAO.LecturerCourseDAO;

public class LecturerCourseController {

    private LecturerCourseDAO lecturerCourseDAO = new LecturerCourseDAO();

    public void assignCourse(int staffID, String coursecode) {
        lecturerCourseDAO.assignCourse(staffID, coursecode);
    }
}

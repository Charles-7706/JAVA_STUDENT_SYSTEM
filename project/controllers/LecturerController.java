package project.controllers;

import project.DAO.LecturerDAO;
import project.model.Lecturer;

public class LecturerController {

    private LecturerDAO lecturerDAO = new LecturerDAO();

    public int addLecturer(String name, int staffID) {
        Lecturer lecturer = new Lecturer(name, staffID);
        return lecturerDAO.saveLecturer(lecturer);
    }

    public java.util.List<Lecturer> getAllLecturers() {
        return lecturerDAO.getAllLecturers();
    }
}

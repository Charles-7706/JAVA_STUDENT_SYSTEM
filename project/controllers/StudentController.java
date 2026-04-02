package project.controllers;

import project.DAO.StudentDAO;
import project.model.Student;
import java.util.List;

public class StudentController {

    private StudentDAO studentDAO = new StudentDAO();

    public int addStudent(String name, int studentID, String programme) {
        Student student = new Student(name, studentID, programme);
        return studentDAO.saveStudent(student);
    }

    public Student searchStudent(int studentID) {
        return studentDAO.searchStudent(studentID);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
}

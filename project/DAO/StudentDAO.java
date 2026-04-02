package project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import project.model.Student;

public class StudentDAO {

    private PersonDAO personDAO = new PersonDAO();

    public int saveStudent(Student student) {
        try {
            int personID = student.getStudentID();
            if (personID <= 0) {
                personID = personDAO.savePerson(student);
                if (personID == -1) return -1;
            }

            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO students(studentID, programme) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, personID);
            ps.setString(2, student.getProgramme());
            ps.executeUpdate();
            return personID;

        } catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Student searchStudent(int id) {
        Student student = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT p.ID, p.name, s.programme FROM person p JOIN students s ON p.ID = s.studentID WHERE s.studentID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                student = new Student(rs.getString("name"), rs.getInt("ID"), rs.getString("programme"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT p.ID, p.name, s.programme FROM person p JOIN students s ON p.ID = s.studentID";
            ResultSet rs = con.prepareStatement(sql).executeQuery();
            while(rs.next()) {
                students.add(new Student(rs.getString("name"), rs.getInt("ID"), rs.getString("programme")));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}

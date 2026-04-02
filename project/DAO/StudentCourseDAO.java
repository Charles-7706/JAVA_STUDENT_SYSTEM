package project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentCourseDAO {

    public void registerCourse(int studentID, String coursecode) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO student_courses(studentID, coursecode) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentID);
            ps.setString(2, coursecode);
            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public java.util.List<String> getCoursesForStudent(int studentID) {
        java.util.List<String> courseCodes = new java.util.ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT coursecode FROM student_courses WHERE studentID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentID);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                courseCodes.add(rs.getString("coursecode"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseCodes;
    }
}
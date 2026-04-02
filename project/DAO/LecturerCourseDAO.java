package project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LecturerCourseDAO {

    public void assignCourse(int staffID, String coursecode) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO lecturer_courses(staffID, coursecode) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, staffID);
            ps.setString(2, coursecode);

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

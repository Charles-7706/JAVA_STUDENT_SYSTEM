package project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import project.model.Lecturer;

public class LecturerDAO {

    private PersonDAO personDAO = new PersonDAO();

    public int saveLecturer(Lecturer lecturer) {
        try {
            int staffID = lecturer.getStaffID();
            if (staffID <= 0) {
                staffID = personDAO.savePerson(lecturer);
                if (staffID == -1) return -1;
            }

            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO lecturers(staffID) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, staffID);
            ps.executeUpdate();
            return staffID;

        } catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public java.util.List<Lecturer> getAllLecturers() {
        java.util.List<Lecturer> lecturers = new java.util.ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT p.ID, p.name FROM person p JOIN lecturers l ON p.ID = l.staffID";
            PreparedStatement ps = con.prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lecturers.add(new Lecturer(rs.getString("name"), rs.getInt("ID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lecturers;
    }
}

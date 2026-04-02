package project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResultSlipDAO {

    public static class ResultSlipEntry {
        public int studentID;
        public String studentName;
        public String programme;
        public String courseCode;
        public String courseName;
        public int catMarks;
        public int examMarks;

        public ResultSlipEntry(int studentID, String studentName, String programme,
                               String courseCode, String courseName, int catMarks, int examMarks) {
            this.studentID = studentID;
            this.studentName = studentName;
            this.programme = programme;
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.catMarks = catMarks;
            this.examMarks = examMarks;
        }

        public int getTotalMarks() { return catMarks + examMarks; }

        public String getGrade() {
            int total = getTotalMarks();
            if(total >= 70) return "A";
            else if(total >= 60) return "B";
            else if(total >= 50) return "C";
            else if(total >= 40) return "D";
            else return "F";
        }
    }

    public List<ResultSlipEntry> generateResultSlip(int studentID) {
        List<ResultSlipEntry> entries = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT p.ID AS studentID, p.name AS studentName, s.programme, " +
                         "c.coursecode, c.coursename, sc.catmarks, sc.exammarks " +
                         "FROM person p " +
                         "JOIN students s ON p.ID = s.studentID " +
                         "JOIN score sc ON s.studentID = sc.studentID " +
                         "JOIN courses c ON sc.coursecode = c.coursecode " +
                         "WHERE p.ID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                entries.add(new ResultSlipEntry(
                    rs.getInt("studentID"),
                    rs.getString("studentName"),
                    rs.getString("programme"),
                    rs.getString("coursecode"),
                    rs.getString("coursename"),
                    rs.getInt("catmarks"),
                    rs.getInt("exammarks")
                ));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entries;
    }
}

package project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import project.model.Score;

public class ScoreDAO {

    public void saveScore(Score score) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO score(studentID, coursecode, catmarks, exammarks) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, score.getStudentID());
            ps.setString(2, score.getCourseCode());
            ps.setInt(3, score.getCatMarks());
            ps.setInt(4, score.getExamMarks());

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

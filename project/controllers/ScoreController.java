package project.controllers;

import project.DAO.ScoreDAO;
import project.model.Score;

public class ScoreController {

    private ScoreDAO scoreDAO = new ScoreDAO();

    public void saveScore(int studentID, String coursecode, int cat, int exam) {

        if(cat > 30 || exam > 70) {
            System.out.println("Invalid marks");
            return;
        }

        Score score = new Score(studentID, coursecode, cat, exam);

        scoreDAO.saveScore(score);
    }

    public String calculateGrade(int cat, int exam) {
    int total = cat + exam;

    if(total >= 70) return "A";
    else if(total >= 60) return "B";
    else if(total >= 50) return "C";
    else if(total >= 40) return "D";
    else return "F";
}
}

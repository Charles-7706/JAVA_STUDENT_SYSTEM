package GroupProject.model;

public class Course {
    private String courseName;
    private String courseCode;
    private Score score;

    public Course(String courseName, String courseCode, Score score) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.score = score;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public float getGrade() {
        return score.getTotalScore();
    }
}

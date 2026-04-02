package project.model;

public class Score {
    private int studentID;
    private String courseCode;
    private int cat;
    private int exam;

    public Score(int studentID, String courseCode, int cat, int exam) {
        this.studentID = studentID;
        this.courseCode = courseCode;
        this.cat = cat;
        this.exam = exam;
    }

    public int getStudentID() { return studentID; }
    public String getCourseCode() { return courseCode; }

    public int getCat() {
        return cat;
    }

    public int getCatMarks() { return cat; }

    public void setCat(int cat) {
        if (cat <= 30) {
            this.cat = cat;
        } else {
            System.out.println("CAT score cannot be greater than 30.");
        }
    }

    public int getExam() {
        return exam;
    }

    public int getExamMarks() { return exam; }

    public void setExam(int exam) {
        if (exam <= 70) {
            this.exam = exam;
        } else {
            System.out.println("Exam score cannot be greater than 70.");
        }
    }

    public int getTotalScore() {
        return cat + exam;
    }


}

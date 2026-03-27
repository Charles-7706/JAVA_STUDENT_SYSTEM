package GroupProject.model;

public class Score {
    private int cat;
    private int exam;

    public Score(int cat, int exam) {
        this.cat = cat;
        this.exam = exam;
    }

    public int getCat() {
        return cat;
    }

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

package project.model;

public class Student extends Person {

    private int studentID;
    private String programme;
    private final Course[] courses;

    public Student(String name, int studentID, String programme) {
        super(name);
        this.studentID = studentID;
        this.programme = programme;
        this.courses = new Course[5];
    }

    public Student(String name, String programme) {
        super(name);
        this.studentID = 0;
        this.programme = programme;
        this.courses = new Course[5];
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                courses[i] = course;
                return;
            }
        }
        System.out.println("Maximum 5 courses reached");
    }
}


package GroupProject.model;

public class Student extends Person {
    private int studentID;
    private String programme;
    private Course[] courses;

    public Student(String name, int studentID, String programme) {
        super(name); // Call the superclass constructor
        this.studentID = studentID;
        this.programme = programme;
        this.courses = new Course[5]; // Initialize the courses array with a fixed size

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

    // student methods
    public void enrollCourse(Course course) {
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                courses[i] = course;
                break;
            }
        }
    }
    // get all courses
    public Course[] getCourses() {
        return courses;
    }
    // generate transcript
    public String generateTranscript() {
        StringBuilder transcript = new StringBuilder();
        transcript.append("Transcript for ").append(getName()).append(" (").append(studentID).append(")\n");
        transcript.append("Programme: ").append(programme).append("\n");
        transcript.append("Courses:\n");
        for (Course course : courses) {
            if (course != null) {
                transcript.append(course.getCourseName()).append(" (").append(course.getCourseCode()).append("): CAT=").append(course.getScore().getCat()).append(", Exam=").append(course.getScore().getExam()).append(", Total=").append(course.getScore().getTotalScore()).append("\n");
            }
        }
        return transcript.toString();
    }
}

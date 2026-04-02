package project.model;

public class Lecturer extends Person {

    private int staffID;
    private Course[] courses;

    public Lecturer(String name, int staffID) {
        super(name);
        this.staffID = staffID;
        this.courses = new Course[10]; // example size
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    // lecturer methods
    // view enrolled courses
    public void viewEnrolledCourses() {
        System.out.println("Courses taught by " + getName() + ":");
        for (Course course : courses) {
            if (course != null) {
                System.out.println(course.getCourseName() + " (" + course.getCourseCode() + ")");
            }
        }
    }
    // assign grades to students
    public void assignGrade(Student student, Course course, Score score) {
        for (Course c : student.getCourses()) {
            if (c != null && c.getCourseCode().equals(course.getCourseCode())) {
                c.setScore(score);
                System.out.println("Grade assigned to " + student.getName() + " for " + course.getCourseName());
                return;
            }
        }
        System.out.println(student.getName() + " is not enrolled in " + course.getCourseName());
    }

}

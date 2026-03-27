package GroupProject.model;

public class LecturerTest {
    public static void main(String[] args) {
        // Create a student
        Student student = new Student("John Doe", 12345, "Computer Science");

        // Create courses
        Course course1 = new Course("Data Structures", "CS101", new Score(25, 60));
        Course course2 = new Course("Algorithms", "CS102", new Score(28, 65));

        // Enroll student in courses
        student.enrollCourse(course1);
        student.enrollCourse(course2);

        // Create lecturer with courses
        Course[] lecturerCourses = {course1, course2};
        Lecturer lecturer = new Lecturer("Dr. Smith", lecturerCourses);

        // Test assignGrade: assign a new score to course1
        Score newScore = new Score(30, 70);
        lecturer.assignGrade(student, course1, newScore);

        // Verify the score was assigned
        Course[] studentCourses = student.getCourses();
        boolean scoreAssigned = false;
        for (Course c : studentCourses) {
            if (c != null && c.getCourseCode().equals("CS101")) {
                if (c.getScore().getCat() == 30 && c.getScore().getExam() == 70) {
                    scoreAssigned = true;
                    System.out.println("Test PASSED: Score assigned correctly.");
                }
                break;
            }
        }
        if (!scoreAssigned) {
            System.out.println("Test FAILED: Score not assigned correctly.");
        }

        // Test assignGrade for a course the student is not enrolled in
        Course course3 = new Course("Databases", "CS103", new Score(20, 50));
        lecturer.assignGrade(student, course3, new Score(25, 55)); // Should print not enrolled message

        // Generate and print transcript to verify
        System.out.println("\nStudent Transcript:");
        System.out.println(student.generateTranscript());
    }
}
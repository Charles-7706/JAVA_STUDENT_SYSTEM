package GroupProject.model;

import javax.swing.*;
import java.awt.*;

public class StudentPortal {
    public static void main(String[] args) {
        // Create a student instance (you can modify this to get data from elsewhere)
        Student student = new Student("Charles Njau", 12345, "Computer Science");

        JFrame frame = new JFrame("Student Portal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Use null layout
        frame.setSize(400, 300);
        frame.setVisible(true);

        JLabel name = new JLabel("Name: " + student.getName());
        name.setFont(new Font("Arial", Font.BOLD, 24));
        name.setBounds(10, 10, 200, 50); // Set position and size of the label
        frame.add(name);

        JLabel studentID = new JLabel("Student ID: " + student.getStudentID());
        studentID.setFont(new Font("Arial", Font.PLAIN, 18));
        studentID.setBounds(10, 70, 200, 30); // Set position and size of the label
        frame.add(studentID);

        JLabel programme = new JLabel("Programme: " + student.getProgramme());
        programme.setFont(new Font("Arial", Font.PLAIN, 18));
        programme.setBounds(10, 110, 300, 30); // Set position and size of the label
        frame.add(programme);

        JLabel courses = new JLabel("Enrolled Courses:");
        courses.setFont(new Font("Arial", Font.BOLD, 18));  
        courses.setBounds(10, 150, 200, 30); // Set position and size of the label
        frame.add(courses);

        JTable courseTable = new JTable(new Object[][] {
                {"Data Structures", "CS101", "85", "90", "87.5"},
                {"Algorithms", "CS102", "90", "95", "92.5"}
        }, new String[] {"Course Name", "Course Code", "CAT", "Exam", "Total"});
        JScrollPane scrollPane = new JScrollPane(courseTable);
        scrollPane.setBounds(10, 190, 380, 200); // Set position and size of the scroll pane
        frame.add(scrollPane);

        // Generate Transcript button
        JButton transcriptButton = new JButton("Generate Transcript");
        transcriptButton.setBounds(10, 400, 150, 30); // Set position and size of the button
        frame.add(transcriptButton);


    }
}

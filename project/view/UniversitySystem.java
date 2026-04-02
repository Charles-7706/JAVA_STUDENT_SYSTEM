package project.view;

import javax.swing.*;
import java.awt.*;

public class UniversitySystem extends JFrame {

    public UniversitySystem() {
        setTitle("University Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the Tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add Tabs
        tabbedPane.addTab("Students", new StudentPanel());
        tabbedPane.addTab("Lecturers", new LecturerPanel());
        tabbedPane.addTab("Courses", new CoursePanel());
        tabbedPane.addTab("Results", new ResultsPanel());

        add(tabbedPane);
    }

    public static void main(String[] args) {
        // Set System Look and Feel for a modern appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new UniversitySystem().setVisible(true);
        });
    }
}

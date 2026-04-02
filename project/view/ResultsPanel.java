package project.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import project.controllers.CourseController;
import project.controllers.ScoreController;
import project.controllers.StudentController;
import project.controllers.StudentCourseController;
import project.model.Course;
import project.model.Student;

public class ResultsPanel extends JPanel {
    public ResultsPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Student:"), gbc);
        gbc.gridx = 1;
        StudentController studentController = new StudentController();
        List<Student> students = studentController.getAllStudents();
        JComboBox<String> studentCombo = new JComboBox<>();
        for (Student s : students) {
            studentCombo.addItem(s.getStudentID() + " - " + s.getName());
        }
        inputPanel.add(studentCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Course:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> courseCombo = new JComboBox<>();
        inputPanel.add(courseCombo, gbc);

        // Load only courses assigned to selected student
        StudentCourseController studentCourseController = new StudentCourseController();
        studentCombo.addActionListener(e -> {
            courseCombo.removeAllItems();
            if (studentCombo.getSelectedItem() == null) return;
            String studentItem = (String) studentCombo.getSelectedItem();
            int studentId = Integer.parseInt(studentItem.split(" - ")[0].trim());
            java.util.List<String> studentCourses = studentCourseController.getCoursesForStudent(studentId);
            if (studentCourses.isEmpty()) {
                courseCombo.addItem("<no courses assigned>");
                courseCombo.setEnabled(false);
            } else {
                courseCombo.setEnabled(true);
                for (String sc : studentCourses) {
                    courseCombo.addItem(sc);
                }
            }
        });

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("CAT (0-30):"), gbc);
        gbc.gridx = 1;
        JTextField txtCAT = new JTextField(5);
        inputPanel.add(txtCAT, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(new JLabel("Exam (0-70):"), gbc);
        gbc.gridx = 1;
        JTextField txtExam = new JTextField(5);
        inputPanel.add(txtExam, gbc);

        gbc.gridx = 2; gbc.gridy = 1; gbc.gridheight = 3;
        JButton btnAddScore = new JButton("Add Score");
        inputPanel.add(btnAddScore, gbc);

        String[] scoreCols = {"Student ID", "Course", "CAT", "Exam", "Total", "Grade"};
        DefaultTableModel scoreModel = new DefaultTableModel(scoreCols, 0);
        JTable tblScores = new JTable(scoreModel);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(tblScores), BorderLayout.CENTER);

        ScoreController scoreController = new ScoreController();

        btnAddScore.addActionListener(e -> {
            if (studentCombo.getSelectedItem() == null || courseCombo.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(ResultsPanel.this, "Select student and course.");
                return;
            }
            String studentItem = (String) studentCombo.getSelectedItem();
            int studentId = Integer.parseInt(studentItem.split(" - ")[0].trim());
            String courseCode = courseCombo.getSelectedItem().toString();
            if ("<no courses assigned>".equals(courseCode)) {
                JOptionPane.showMessageDialog(ResultsPanel.this, "Selected student has no assigned courses.");
                return;
            }

            int cat, exam;
            try {
                cat = Integer.parseInt(txtCAT.getText().trim());
                exam = Integer.parseInt(txtExam.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ResultsPanel.this, "Enter valid numeric CAT and Exam scores.");
                return;
            }

            if (cat < 0 || cat > 30 || exam < 0 || exam > 70) {
                JOptionPane.showMessageDialog(ResultsPanel.this, "CAT must be 0-30 and Exam must be 0-70.");
                return;
            }

            scoreController.saveScore(studentId, courseCode, cat, exam);
            int total = cat + exam;
            String grade = scoreController.calculateGrade(cat, exam);
            scoreModel.addRow(new Object[]{ studentId, courseCode, cat, exam, total, grade });

            txtCAT.setText("");
            txtExam.setText("");
            JOptionPane.showMessageDialog(ResultsPanel.this, "Score saved for student " + studentId);
        });
    }
}

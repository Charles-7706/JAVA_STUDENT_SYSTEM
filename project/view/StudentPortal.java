package project.view;

import project.DAO.ResultSlipDAO;
import project.DAO.ResultSlipDAO.ResultSlipEntry;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentPortal {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Enter Student ID:", "Student Portal", JOptionPane.QUESTION_MESSAGE);
        if (input == null || input.trim().isEmpty()) return;

        int id;
        try {
            id = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID");
            return;
        }

        ResultSlipDAO dao = new ResultSlipDAO();
        List<ResultSlipEntry> entries = dao.generateResultSlip(id);

        if (entries.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No data found for student ID: " + id);
            return;
        }

        ResultSlipEntry first = entries.get(0);

        JFrame frame = new JFrame("Student Portal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(650, 550);
        frame.getContentPane().setBackground(new Color(255, 255, 255));

        JLabel name = new JLabel("Name: " + first.studentName);
        name.setFont(new Font("Arial", Font.BOLD, 22));
        name.setBounds(10, 10, 400, 40);
        frame.add(name);

        JLabel studentID = new JLabel("Student ID: " + first.studentID);
        studentID.setFont(new Font("Arial", Font.PLAIN, 16));
        studentID.setBounds(10, 55, 300, 25);
        frame.add(studentID);

        JLabel programme = new JLabel("Programme: " + first.programme);
        programme.setFont(new Font("Arial", Font.PLAIN, 16));
        programme.setBounds(10, 85, 400, 25);
        frame.add(programme);

        JLabel coursesLabel = new JLabel("Enrolled Courses:");
        coursesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        coursesLabel.setBounds(10, 120, 200, 25);
        frame.add(coursesLabel);

        // table rows + 1 summary row
        Object[][] tableData = new Object[entries.size() + 1][6];
        int totalCat = 0, totalExam = 0;
        for (int i = 0; i < entries.size(); i++) {
            ResultSlipEntry e = entries.get(i);
            tableData[i] = new Object[]{ e.courseName, e.courseCode, e.catMarks, e.examMarks, e.getTotalMarks(), e.getGrade() };
            totalCat += e.catMarks;
            totalExam += e.examMarks;
        }
        tableData[entries.size()] = new Object[]{ "TOTAL", "", totalCat, totalExam, totalCat + totalExam, "" };

        JTable courseTable = new JTable(tableData, new String[]{ "Course Name", "Code", "CAT", "Exam", "Total", "Grade" });
        courseTable.setFont(new Font("Arial", Font.PLAIN, 14));
        courseTable.setRowHeight(25);
        courseTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(courseTable);
        scrollPane.setBounds(10, 150, 615, 300);
        frame.add(scrollPane);

        frame.setVisible(true);
    }
}

package project.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import project.controllers.CourseController;
import project.model.Course;

public class CoursePanel extends JPanel {
    public CoursePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Course Code:"), gbc);
        gbc.gridx = 1;
        JTextField txtCode = new JTextField(12);
        inputPanel.add(txtCode, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Course Name:"), gbc);
        gbc.gridx = 1;
        JTextField txtName = new JTextField(20);
        inputPanel.add(txtName, gbc);

        gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 2;
        JButton btnAddCourse = new JButton("Add Course");
        inputPanel.add(btnAddCourse, gbc);

        String[] courseCols = {"Course Code", "Course Name"};
        DefaultTableModel courseModel = new DefaultTableModel(courseCols, 0);
        CourseController courseController = new CourseController();
        for (Course c : courseController.getAllCourses()) {
            courseModel.addRow(new Object[]{ c.getCourseCode(), c.getCourseName() });
        }

        JTable tblCourses = new JTable(courseModel);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(tblCourses), BorderLayout.CENTER);

        btnAddCourse.addActionListener(e -> {
            String code = txtCode.getText().trim();
            String name = txtName.getText().trim();
            if (code.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(CoursePanel.this, "Please fill course code and name.");
                return;
            }
            courseController.addCourse(code, name);
            courseModel.addRow(new Object[]{code, name});
            txtCode.setText("");
            txtName.setText("");
            JOptionPane.showMessageDialog(CoursePanel.this, "Course added: " + code);
        });
    }
}

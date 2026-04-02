package project.view;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import project.controllers.*;
import project.model.*;

public class StudentPanel extends JPanel {
    private int selectedStudentId = -1;

    public StudentPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- TOP SECTION: Input Form ---
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        JTextField txtName = new JTextField(15);
        inputPanel.add(txtName, gbc);

        // Programme
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Programme:"), gbc);
        gbc.gridx = 1;
        JTextField txtProg = new JTextField(15);
        inputPanel.add(txtProg, gbc);

        // Add Student Button
        gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 2;
        JButton btnAddStudent = new JButton("Add Student");
        inputPanel.add(btnAddStudent, gbc);

        // --- CENTER SECTION: Tables ---
        JPanel tablePanel = new JPanel(new GridLayout(2, 1, 0, 10));

        // Student List Table
        String[] studentCols = {"ID", "Name", "Programme"};
        DefaultTableModel studentModel = new DefaultTableModel(studentCols, 0);
        StudentController sc = new StudentController();
        for (Student s : sc.getAllStudents()) {
            studentModel.addRow(new Object[]{ s.getStudentID(), s.getName(), s.getProgramme() });
        }
        JTable tblStudents = new JTable(studentModel);
        tablePanel.add(new JScrollPane(tblStudents));

        // Student Courses Table (Bottom half of center)
        JPanel bottomTablePanel = new JPanel(new BorderLayout());
        
        // Course Selection Header
        JPanel courseSelectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        courseSelectPanel.add(new JLabel("Course List:"));
        CourseController cc = new CourseController();
        System.out.println("Courses in DB:");
        List<project.model.Course> courses = cc.getAllCourses();
        String[] courseArray = new String[courses.size()];
        for (int i = 0; i < courses.size(); i++) {
            courseArray[i] = courses.get(i).getCourseCode();
        }
        JComboBox<String> course = new JComboBox<>(courseArray);
        List<Course> selectedCourses = new ArrayList<>();
        String[] courseCols = {"Course Code", "Course Name"};
        DefaultTableModel courseModel = new DefaultTableModel(courseCols, 0);
        JTable tblStudentCourses = new JTable(courseModel);

        course.addItemListener(e -> {
            // Only trigger once per selection (when the item is SELECTED)
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selected = course.getSelectedItem().toString();
                for (Course c1 : courses) {
                    if (selected.equals(c1.getCourseCode()) && !selectedCourses.contains(c1)) {
                        courseModel.addRow(new Object[]{ c1.getCourseCode(), c1.getCourseName() });
                        selectedCourses.add(c1);
                    }
                }
            }
        });

        courseSelectPanel.add(course);
        JButton btnAddCourses = new JButton("Add Courses to Student");
        btnAddCourses.addActionListener(e -> {
            if (selectedStudentId <= 0) {
                JOptionPane.showMessageDialog(StudentPanel.this, "Please add/select a student first.");
                return;
            }
            if (selectedCourses.isEmpty()) {
                JOptionPane.showMessageDialog(StudentPanel.this, "No courses selected to add.");
                return;
            }
            StudentCourseController scc = new StudentCourseController();
            for (Course c1 : selectedCourses) {
                scc.registerCourse(selectedStudentId, c1.getCourseCode());
            }
            JOptionPane.showMessageDialog(StudentPanel.this, "Added " + selectedCourses.size() + " courses to student.");
        });
        courseSelectPanel.add(btnAddCourses);
        
        
        bottomTablePanel.add(courseSelectPanel, BorderLayout.NORTH);
        bottomTablePanel.add(new JScrollPane(tblStudentCourses), BorderLayout.CENTER);
        
        tablePanel.add(bottomTablePanel);

        // Add components to Main Panel
        add(inputPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        //listeners


btnAddStudent.addActionListener(e -> {
    String name = txtName.getText().trim();
    String prog = txtProg.getText().trim();
    if (name.isEmpty() || prog.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields.");
        return;
    }
    PersonController personController = new PersonController();
    int id = personController.addPerson(name);
    StudentController studentController = new StudentController();
    int savedId = studentController.addStudent(name, id, prog);
    selectedStudentId = savedId > 0 ? savedId : id;

    JOptionPane.showMessageDialog(this, "Student " + name + " added");
    txtName.setText("");
    txtProg.setText("");
    studentModel.setRowCount(0);
    for (Student s : studentController.getAllStudents()) {
        studentModel.addRow(new Object[]{ s.getStudentID(), s.getName(), s.getProgramme() });
    }
});

    }
}

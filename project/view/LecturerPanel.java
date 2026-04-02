package project.view;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import project.controllers.*;
import project.model.Course;
import project.model.Lecturer;
import project.model.Student;

public class LecturerPanel extends JPanel {
    private int selectedLecturerId = -1;

    public LecturerPanel() {
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

        // Add lecturer Button
        gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 2;
        JButton btnAddLecturer = new JButton("Add Lecturer");
        inputPanel.add(btnAddLecturer, gbc);

        // --- CENTER SECTION: Tables ---
        JPanel tablePanel = new JPanel(new GridLayout(2, 1, 0, 10));

        String[] lecturerCols = {"ID", "Name"};
        DefaultTableModel lecturerModel = new DefaultTableModel(lecturerCols, 0);
        LecturerController lecturerController = new LecturerController();
        for (Lecturer l : lecturerController.getAllLecturers()) {
            lecturerModel.addRow(new Object[]{l.getStaffID(), l.getName()});
        }
        JTable tblLecturers = new JTable(lecturerModel);
        tblLecturers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblLecturers.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblLecturers.getSelectedRow() >= 0) {
                selectedLecturerId = (int) lecturerModel.getValueAt(tblLecturers.getSelectedRow(), 0);
            }
        });

        tablePanel.add(new JScrollPane(tblLecturers));

        // Lecturer courses assignment section (select multiple courses like student)
        JPanel bottomTablePanel = new JPanel(new BorderLayout());
        JPanel courseSelectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        courseSelectPanel.add(new JLabel("Course List:"));

        CourseController courseController = new CourseController();
        List<Course> courses = courseController.getAllCourses();
        String[] courseArray = new String[courses.size()];
        for (int i = 0; i < courses.size(); i++) {
            courseArray[i] = courses.get(i).getCourseCode();
        }
        JComboBox<String> course = new JComboBox<>(courseArray);
        List<Course> selectedCourses = new ArrayList<>();
        String[] selectedCourseCols = {"Course Code", "Course Name"};
        DefaultTableModel selectedCourseModel = new DefaultTableModel(selectedCourseCols, 0);
        JTable tblSelectedCourses = new JTable(selectedCourseModel);

        course.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selected = course.getSelectedItem().toString();
                for (Course c : courses) {
                    if (selected.equals(c.getCourseCode()) && !selectedCourses.contains(c)) {
                        selectedCourseModel.addRow(new Object[]{c.getCourseCode(), c.getCourseName()});
                        selectedCourses.add(c);
                        break;
                    }
                }
            }
        });

        JButton btnAddCourses = new JButton("Add Courses to Lecturer");

        courseSelectPanel.add(course);
        courseSelectPanel.add(btnAddCourses);

        bottomTablePanel.add(courseSelectPanel, BorderLayout.NORTH);
        bottomTablePanel.add(new JScrollPane(tblSelectedCourses), BorderLayout.CENTER);

        tablePanel.add(bottomTablePanel);


        add(inputPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);

        btnAddLecturer.addActionListener(e -> {
            String name = txtName.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(LecturerPanel.this, "Please fill in lecturer name.");
                return;
            }

            PersonController personController = new PersonController();
            int id = personController.addPerson(name);
            if (id <= 0) {
                JOptionPane.showMessageDialog(LecturerPanel.this, "Failed to add lecturer.");
                return;
            }
            int savedId = lecturerController.addLecturer(name, id);
            selectedLecturerId = savedId > 0 ? savedId : id;
            lecturerModel.setRowCount(0);
            for (Lecturer l : lecturerController.getAllLecturers()) {
                lecturerModel.addRow(new Object[]{l.getStaffID(), l.getName()});
            }
            txtName.setText("");
            JOptionPane.showMessageDialog(LecturerPanel.this, "Lecturer added with ID " + id);
        });

        btnAddCourses.addActionListener(e -> {
            if (selectedLecturerId <= 0) {
                JOptionPane.showMessageDialog(LecturerPanel.this, "Select/add a lecturer first.");
                return;
            }
            if (selectedCourses.isEmpty()) {
                JOptionPane.showMessageDialog(LecturerPanel.this, "No courses selected to add.");
                return;
            }

            LecturerCourseController lecturerCourseController = new LecturerCourseController();
            for (Course c : selectedCourses) {
                lecturerCourseController.assignCourse(selectedLecturerId, c.getCourseCode());
            }

            String added = selectedCourses.size() == 1 ? "1 course" : selectedCourses.size() + " courses";
            selectedCourses.clear();
            selectedCourseModel.setRowCount(0);
            JOptionPane.showMessageDialog(LecturerPanel.this, "Added " + added + " to lecturer " + selectedLecturerId);
        });
    }
}

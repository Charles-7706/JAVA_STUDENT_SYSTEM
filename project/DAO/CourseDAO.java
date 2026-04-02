package project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import project.model.Course;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

public class CourseDAO {

    public void saveCourse(Course course) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO courses(coursecode, coursename) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, course.getCourseCode());
            ps.setString(2, course.getCourseName());

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM courses";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String code = rs.getString("coursecode");
                String name = rs.getString("coursename");
                Course course = new Course(name, code, null);
                courses.add(course);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return courses;
    }
}

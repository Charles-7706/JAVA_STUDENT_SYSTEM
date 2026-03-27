package GroupProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database {
    static final String DB_URL = "jdbc:mysql://localhost:3306/student_system";
    static final String USER = "root";
    static final String PASS = "password";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to the database successfully!");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM students"; // Example query
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Student Records:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("studentID") + ", Programme: " + rs.getString("programme"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

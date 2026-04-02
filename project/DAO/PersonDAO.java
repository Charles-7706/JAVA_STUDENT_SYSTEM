package project.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import project.model.Person;
import project.model.Student;

public class PersonDAO {

    public int savePerson(Person person) {
        int generatedID = -1;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO person(name) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getName());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) generatedID = keys.getInt(1);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return generatedID;
    }

    public Person searchPerson(int id) {
        Person person = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM person WHERE ID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                person = new Student(rs.getString("name"), rs.getInt("ID"), "");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println(person != null ? person.getName() : "Person not found");
        return person;
    }
}

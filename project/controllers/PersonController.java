package project.controllers;

import project.DAO.PersonDAO;
import project.model.Student;

public class PersonController {

    private PersonDAO personDAO = new PersonDAO();

    public int addPerson(String name) {
        Student person = new Student(name, 0, "");
        return personDAO.savePerson(person);
    }
}

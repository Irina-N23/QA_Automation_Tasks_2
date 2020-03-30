package automation.errorsandexceptions.universitycomponents;

import automation.errorsandexceptions.exceptions.GroupNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

public class Faculty {
    private String facultyName;
    private ArrayList<Group> listOfGroups = new ArrayList<>();

    public Faculty(String facultyName) {
        setFacultyName(facultyName);
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        if (facultyName != null && !facultyName.isEmpty()) {
            this.facultyName = facultyName;
        } else {
            throw new IllegalArgumentException("\n(!)\tINCORRECT FACULTY NAME: NULL OR EMPTY.");
        }
    }

    public void addNewGroupToGroupList(Group newGroup) {
        listOfGroups.add(newGroup);
    }

    public ArrayList<Group> getListOfGroupsInFaculty() throws GroupNotFoundException {
        if (listOfGroups.isEmpty()) {
            throw new GroupNotFoundException("\n(!)\tNO GROUP HAS BEEN FOUND IN THE FACULTY OF "
                                             + getFacultyName().toUpperCase() + ".");
        }
        return listOfGroups;
    }

    public void printListOfGroupsInFaculty() throws GroupNotFoundException {
        System.out.println("\nHere is a list of groups in this faculty: "
                           + getListOfGroupsInFaculty());
    }

    @Override
    public String toString() {
        return "Faculty of " + facultyName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Faculty faculty = (Faculty) object;
        return Objects.equals(facultyName, faculty.facultyName)
                && Objects.equals(listOfGroups, faculty.listOfGroups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyName, listOfGroups);
    }
}
package automation.errorsandexceptions.universitycomponents;

import automation.errorsandexceptions.exceptions.*;
import java.util.*;

public class University {
    private String universityName;
    private Set<Faculty> listOfFaculties = new HashSet<>();

    public University(String universityName) {
        setUniversityName(universityName);
    }

    public void setUniversityName(String universityName) {
        if (universityName != null && !universityName.isEmpty()) {
            this.universityName = universityName;
        } else {
            System.out.println("\u001B[31m(!)\tIncorrect university name: null or empty.\u001B[0m");
        }
    }

    public String getUniversityName() {
        return universityName;
    }

    public void addNewFacultyToFacultyList(Faculty newFaculty) {
        listOfFaculties.add(newFaculty);
    }

    public Set<Faculty> getListOfFaculties() throws FacultyNotFoundException {
        if (listOfFaculties.isEmpty()) {
            throw new FacultyNotFoundException("\n(!)\tNO FACULTY HAS BEEN FOUND IN THE LIST OF "
                                               + "FACULTIES OF THE " + getUniversityName() + ".");
        }
        return listOfFaculties;
    }

    public void printListOfFaculties() throws FacultyNotFoundException {
        System.out.println("\nHere is a total list of faculties in the university:\t"
                           + getListOfFaculties());
    }

    public Faculty getSelectedFacultyFromTotalList(String nameOfSelectedFaculty)
            throws FacultyNotFoundException {
        if (nameOfSelectedFaculty == null || nameOfSelectedFaculty.isEmpty()) {
            throw new IllegalArgumentException("\n(!)\tINCORRECT FACULTY NAME: NULL OR EMPTY.");
        }

        Faculty selectedFaculty = null;
        for (Faculty faculty : getListOfFaculties()) {
            if (faculty.getFacultyName().toLowerCase()
                    .contains(nameOfSelectedFaculty.toLowerCase())) {
                selectedFaculty = faculty;
            }
        }

        if (selectedFaculty == null) {
            throw new FacultyNotFoundException("\n(!)\tFACULTY <" + nameOfSelectedFaculty
                                               + "> HAS NOT BEEN FOUND.");
        } else {
            return selectedFaculty;
        }
    }

    public ArrayList<Student> getTotalListOfStudents() throws FacultyNotFoundException,
            GroupNotFoundException, StudentNotFoundException {
        ArrayList<Student> totalListOfStudents = new ArrayList<>();
        for (Faculty faculty : getListOfFaculties()) {
            for (Group group : faculty.getListOfGroupsInFaculty()) {
                totalListOfStudents.addAll(group.getStudentListOfGroup());
            }
        }
        return totalListOfStudents;
    }

    public void printTotalListOfStudents() throws GroupNotFoundException,
            FacultyNotFoundException, StudentNotFoundException {
        System.out.println("\nHere is a total list of students in the university:\n"
                           + getTotalListOfStudents());
    }

    public Student getSelectedStudentFromTotalList(String nameOfSelectedStudent)
            throws FacultyNotFoundException, GroupNotFoundException, StudentNotFoundException {
        if (nameOfSelectedStudent.isEmpty()) {
            throw new IllegalArgumentException("\n(!)\tINCORRECT NAME OF STUDENT: EMPTY.");
        }

        Student selectedStudent = null;
        for (Student student : getTotalListOfStudents()) {
            if (student.getFullName().toLowerCase().contains(nameOfSelectedStudent.toLowerCase())) {
                selectedStudent = student;
            }
        }

        if (selectedStudent == null) {
            throw new StudentNotFoundException("\n(!)\tSTUDENT <" + nameOfSelectedStudent
                                               + "> HAS NOT BEEN FOUND.");
        } else {
            return selectedStudent;
        }
    }

    public ArrayList<Group> getTotalListOfGroups() {
        ArrayList<Group> totalListOfGroups = new ArrayList<>();
        try {
            for (Faculty faculty : getListOfFaculties()) {
                totalListOfGroups.addAll(faculty.getListOfGroupsInFaculty());
            }
        } catch (FacultyNotFoundException | GroupNotFoundException exception) {
            exception.printStackTrace();
        }
        return totalListOfGroups;
    }

    public Group getSelectedGroupFromTotalList(int numberOfSelectedGroup)
            throws GroupNotFoundException {
        if (numberOfSelectedGroup <= 0) {
            System.out.println("\u001B[31m(!)\tIncorrect group number has been inputted: <"
                               + numberOfSelectedGroup
                               + ">. A group number must be a positive integer.\u001B[0m\n");
        }

        Group selectedGroup = null;
        for (Group group : getTotalListOfGroups()) {
            if (group.getGroupNumber() == numberOfSelectedGroup) {
                selectedGroup = group;
            }
        }

        if (selectedGroup == null) {
            throw new GroupNotFoundException("\n(!)\tGROUP <" + numberOfSelectedGroup
                                             + "> HAS NOT BEEN FOUND.");
        } else {
            return selectedGroup;
        }
    }

    public void printListOfUniversityCourses() {
        System.out.println("\nHere is a total list of courses in this university:\t"
                           + Arrays.toString(Course.values()));
    }

    public Course getSelectedCourseFromTotalList(String nameOfSelectedCourse)
            throws CourseNotFoundException {
        for (Course course : Course.values()) {
            if (course.getNameOfCourse().toLowerCase()
                    .contains(nameOfSelectedCourse.toLowerCase())) {
                return course;
            }
        }
        throw new CourseNotFoundException("\n(!)\tCOURSE <" + nameOfSelectedCourse
                                          + "> HAS NOT BEEN FOUND.");
    }

    @Override
    public String toString() {
        return universityName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        University university = (University) object;
        return Objects.equals(universityName, university.universityName)
                && Objects.equals(listOfFaculties, university.listOfFaculties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityName, listOfFaculties);
    }
}
package automation.errorsandexceptions.universitycomponents;

import automation.errorsandexceptions.exceptions.CourseNotFoundException;
import automation.errorsandexceptions.exceptions.StudentNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

public class Group {
    private int groupNumber;
    private ArrayList<Student> studentListOfGroup = new ArrayList<>();

    public Group(int groupNumber) {
        setGroupNumber(groupNumber);
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        if (groupNumber > 0) {
            this.groupNumber = groupNumber;
        } else {
            throw new IllegalArgumentException("\n(!)\tINCORRECT GROUP NUMBER HAS BEEN "
                                               + "INPUTTED: <" + groupNumber + ">. A GROUP NUMBER "
                                               + "MUST BE A POSITIVE INTEGER.");
        }
    }

    public void addNewStudentToStudentListOfGroup(Student newStudent) {
        studentListOfGroup.add(newStudent);
    }

    public ArrayList<Student> getStudentListOfGroup() throws StudentNotFoundException {
        if (studentListOfGroup.isEmpty()) {
            throw new StudentNotFoundException("\n(!)\tNO STUDENT HAS BEEN FOUND "
                                               + "IN THE GROUP № " + getGroupNumber() + ".");
        }
        return studentListOfGroup;
    }

    public HashSet<Course> getListOfAvailableCoursesInGroup()
            throws StudentNotFoundException, CourseNotFoundException {
        HashSet<Course> listOfAvailableCoursesInGroup = new HashSet<>();
        for (Student student : getStudentListOfGroup()) {
            listOfAvailableCoursesInGroup.addAll(student.getGrades().keySet());
        }
        return listOfAvailableCoursesInGroup;
    }

    public void printListOfAvailableCoursesInGroup()
            throws CourseNotFoundException, StudentNotFoundException {
        System.out.println("\nHere is a list of all courses given for this group:\t"
                           + getListOfAvailableCoursesInGroup());
    }

    public Course getSelectedCourseFromAllCoursesInGroup(String nameOfSelectedCourse)
            throws CourseNotFoundException, StudentNotFoundException {
        for (Course course : this.getListOfAvailableCoursesInGroup()) {
            if (course.getNameOfCourse().toLowerCase()
                    .contains(nameOfSelectedCourse.toLowerCase())) {
                return course;
            }
        }
        throw new CourseNotFoundException("\n(!)\tCOURSE <" + nameOfSelectedCourse.toUpperCase()
                                          + "> IS NOT GIVEN FOR <" + this + ">.");
    }

    @Override
    public String toString() {
        return "Group № " + groupNumber;
    }
}
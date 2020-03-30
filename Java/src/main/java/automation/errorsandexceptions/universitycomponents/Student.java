package automation.errorsandexceptions.universitycomponents;

import automation.errorsandexceptions.exceptions.CourseNotFoundException;
import automation.errorsandexceptions.exceptions.IllegalGradeException;
import java.util.HashMap;

public class Student {
    private String fullName;
    private HashMap<Course, Integer> grades = new HashMap<>();

    public Student(String fullName) {
        setFullName(fullName);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName != null && !fullName.isEmpty()) {
            this.fullName = fullName;
        } else {
            throw new IllegalArgumentException("\n(!)\tINCORRECT FULL NAME OF STUDENT: "
                                               + "NULL OR EMPTY.");
        }
    }

    public HashMap<Course, Integer> getGrades() throws CourseNotFoundException {
        if (grades.isEmpty()) {
            throw new CourseNotFoundException("\n(!)\tNO COURSE HAS BEEN FOUND FOR STUDENT <"
                                              + getFullName() + ">.");
        }
        return grades;
    }

    public void addNewCourseAndGradeToStudentRegister(Course newCourse, Integer newGrade)
            throws IllegalGradeException {
        if (newGrade < 1 || newGrade > 10) {
            throw new IllegalGradeException("\n(!)\tINCORRECT GRADE HAS BEEN INPUTTED FOR STUDENT <"
                                            + getFullName() + ">: THE VALID GRADE MUST BE WITHIN "
                                            + "THE RANGE [1, 10].");
        }
        grades.put(newCourse, newGrade);
    }

    @Override
    public String toString() {
        return fullName;
    }
}
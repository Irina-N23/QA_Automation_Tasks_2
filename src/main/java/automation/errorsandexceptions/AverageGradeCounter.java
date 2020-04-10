package automation.errorsandexceptions;

import automation.errorsandexceptions.exceptions.*;
import automation.errorsandexceptions.universitycomponents.*;
import java.util.ArrayList;
import java.util.List;

public class AverageGradeCounter {
    double getAverageGradeForAllCoursesOfStudent(Student student)
            throws CourseNotFoundException {
        List<Integer> grades = new ArrayList<>(student.getGrades().values());
        double sumOfGrades = 0.0;

        for (Integer grade : grades) {
            sumOfGrades += grade;
        }
        return sumOfGrades / grades.size();
    }

    double getAverageGradeForCourseInGroup(Group selectedGroup, Course selectedCourse)
            throws CourseNotFoundException, StudentNotFoundException {
        List<Student> studentListOfChosenGroup = new ArrayList<>(selectedGroup
                                                         .getStudentListOfGroup());
        int quantityOfGrades = 0;
        double sumOfGrades = 0.0;

        for (Student student : studentListOfChosenGroup) {
            if (student.getGrades().containsKey(selectedCourse)) {
                sumOfGrades += student.getGrades().get(selectedCourse);
                quantityOfGrades++;
            }
        }
        return sumOfGrades / quantityOfGrades;
    }

    double getAverageGradeForCourseInUniversity(University university, Course selectedCourse)
            throws FacultyNotFoundException, GroupNotFoundException,
                   CourseNotFoundException, StudentNotFoundException {
        int quantityOfGrades = 0;
        double sumOfGrades = 0.0;

        for (Faculty faculty : university.getListOfFaculties()) {
            for (Group group : faculty.getListOfGroupsInFaculty()) {
                for (Student student : group.getStudentListOfGroup()) {
                    if (student.getGrades().containsKey(selectedCourse)) {
                        sumOfGrades += student.getGrades().get(selectedCourse);
                        quantityOfGrades++;
                    }
                }
            }
        }
        return sumOfGrades / quantityOfGrades;
    }
}
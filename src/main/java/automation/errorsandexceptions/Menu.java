package automation.errorsandexceptions;

import automation.errorsandexceptions.exceptions.*;
import automation.errorsandexceptions.universitycomponents.*;
import java.util.Scanner;

public class Menu {
    private University activatedUniversity;
    private AverageGradeCounter averageGradeCounter = new AverageGradeCounter();
    private Scanner chosenAction = new Scanner(System.in);

    Menu(University activatedUniversity) {
        this.activatedUniversity = activatedUniversity;
    }

    void showMainMenu() {
        System.out.println("Which action do you want to choose?");
        System.out.println("Just input the number of necessary action and press <ENTER> then!");
        System.out.println("\t1. Display an average grade for all courses of a chosen student;");
        System.out.println("\t2. Display an average grade for a chosen course "
                           + "in a chosen group of a chosen faculty;");
        System.out.println("\t3. Display an average grade for a chosen course in the university;");
        System.out.println("\t4. Quit.");
        System.out.print("Choose one:\t");
    }

    void goToChosenAction() throws CourseNotFoundException, GroupNotFoundException,
                                   FacultyNotFoundException, StudentNotFoundException {
        switch (chosenAction.nextLine()) {
            case "1":
                printAverageGradeForAllCoursesOfStudent();
                goBackOrExit();
                break;
            case "2":
                printAverageGradeForCourseInGroup();
                goBackOrExit();
                break;
            case "3":
                printAverageGradeForCourseInUniversity();
                goBackOrExit();
                break;
            case "4":
                completeThisProgram();
            default:
                System.out.print("\n\033[0;91mYou have inputted incorrect value. "
                                 + "Just try again!\033[0m\nYour choice:\t");
                goToChosenAction();
        }
        System.out.println();
    }

    private void completeThisProgram() {
        System.out.print("\nCompleting the program...");
        chosenAction.close();
        System.exit(0);
    }

    private void goBackOrExit() {
        System.out.println("\nWhat's your next action?");
        System.out.println("\t1. Go back to main menu for choosing another action;");
        System.out.println("\t2. Quit.");
        System.out.print("Just input the number of your action and press <ENTER> then:\t");

        switch (chosenAction.nextLine()) {
            case "1":
                System.out.println();
                showMainMenu();
                try {
                    goToChosenAction();
                } catch (CourseNotFoundException | GroupNotFoundException
                         | FacultyNotFoundException | StudentNotFoundException exception) {
                    exception.printStackTrace();
                }
                break;
            case "2":
                completeThisProgram();
            default:
                System.out.println("\n\033[0;91mYou have inputted incorrect data. "
                                   + "Just try again!\033[0m");
                goBackOrExit();
        }
    }

    private void printAverageGradeForAllCoursesOfStudent() throws CourseNotFoundException,
            FacultyNotFoundException, GroupNotFoundException, StudentNotFoundException {
        Scanner scanner = new Scanner(System.in);

        activatedUniversity.printTotalListOfStudents();
        System.out.print("Just input last name of necessary student:\t");
        String inputtedNameOfStudent = scanner.nextLine();
        Student selectedStudent = null;
        try {
            selectedStudent = activatedUniversity.
                                          getSelectedStudentFromTotalList(inputtedNameOfStudent);
        } catch (StudentNotFoundException exception) {
            System.err.println("Student <" + inputtedNameOfStudent + "> has not been found. "
                               + "Just try again and input another name of student!");
            printAverageGradeForAllCoursesOfStudent();
        }

        if (selectedStudent != null) {
            System.out.printf("\033[1;36m\nAn average grade for all courses of %s is "
                              + "\033[4;36m%.2f\033[0m.\n", selectedStudent, averageGradeCounter
                                      .getAverageGradeForAllCoursesOfStudent(selectedStudent));
        }
    }

    private void printAverageGradeForCourseInGroup() throws FacultyNotFoundException,
            GroupNotFoundException, CourseNotFoundException, StudentNotFoundException {
        Scanner scanner = new Scanner(System.in);

        activatedUniversity.printListOfFaculties();
        System.out.print("Just input the name of necessary faculty "
                         + "and press <ENTER> then:\t");
        Faculty selectedFaculty = activatedUniversity
                                      .getSelectedFacultyFromTotalList(scanner.nextLine());

        if (selectedFaculty.getListOfGroupsInFaculty().size() > 1) {
            selectedFaculty.printListOfGroupsInFaculty();
            System.out.print("Which group do you want to choose? Just input its number here"
                             + " and press <ENTER> then:\t");
        } else {
            System.out.print("\nOnly one group has been found in the Faculty of "
                             + selectedFaculty.getFacultyName() + ": "
                             + selectedFaculty.getListOfGroupsInFaculty()
                             + ".\nJust input the number of this group "
                             + "in order to choose it:\t");
        }
        Group selectedGroup;
        if (scanner.hasNextInt()) {
            selectedGroup = activatedUniversity
                                .getSelectedGroupFromTotalList(scanner.nextInt());
        } else {
            throw new IllegalArgumentException("\n(!)\tINCORRECT VALUE HAS BEEN INPUTTED: "
                                               + "NOT A NUMBER.");
        }

        selectedGroup.printListOfAvailableCoursesInGroup();
        System.out.print("Just input the name of necessary course "
                         + "and press <ENTER> then:\t");
        Course selectedCourse = selectedGroup
                                    .getSelectedCourseFromAllCoursesInGroup(scanner.next());

        try {
            System.out.printf("\n\033[1;36mAn average grade for course of %s in the %s "
                              + "of %s is \033[4;36m%.2f\033[0m.\n\n", selectedCourse,
                              selectedGroup, selectedFaculty, averageGradeCounter
                                      .getAverageGradeForCourseInGroup(selectedGroup,
                                                                       selectedCourse));
        } catch (CourseNotFoundException | StudentNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    private void printAverageGradeForCourseInUniversity() throws CourseNotFoundException {
        Scanner scanner = new Scanner(System.in);

        activatedUniversity.printListOfUniversityCourses();
        System.out.print("Just input the name of necessary course "
                         + "and press <ENTER> then:\t");
        Course chosenCourse = activatedUniversity
                                  .getSelectedCourseFromTotalList(scanner.next());
        try {
            System.out.printf("\n\033[1;36mAn average grade for course of %s in the %s "
                              + "is \033[4;36m%.2f\033[0m.\n\n", chosenCourse,
                              activatedUniversity.getUniversityName(), averageGradeCounter
                                      .getAverageGradeForCourseInUniversity(activatedUniversity,
                                                                            chosenCourse));
        } catch (FacultyNotFoundException | GroupNotFoundException
                 | StudentNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
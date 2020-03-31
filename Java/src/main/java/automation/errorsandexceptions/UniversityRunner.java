package automation.errorsandexceptions;

import automation.errorsandexceptions.exceptions.CourseNotFoundException;
import automation.errorsandexceptions.exceptions.FacultyNotFoundException;
import automation.errorsandexceptions.exceptions.GroupNotFoundException;
import automation.errorsandexceptions.exceptions.StudentNotFoundException;
import automation.errorsandexceptions.universitycomponents.*;

public class UniversityRunner {
    public static void main(String[] args) {
        University university = new University("BSU");
        Menu menu = new Menu(UniversityActivator.activateUniversity(university));

        System.out.println("Welcome to our university!");
        menu.showMainMenu();
        try {
            menu.goToChosenAction();
        } catch (CourseNotFoundException | GroupNotFoundException | FacultyNotFoundException
                 | StudentNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
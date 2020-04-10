package automation.errorsandexceptions;

import automation.errorsandexceptions.exceptions.IllegalGradeException;
import automation.errorsandexceptions.universitycomponents.*;

public class UniversityActivator {
    public static University activateUniversity(University universityForActivation) {
        Faculty physics = new Faculty("Physics (PHY)");
        Faculty law = new Faculty("Law (LAW)");
        Faculty economics = new Faculty("Economics (ECON)");

        universityForActivation.addNewFacultyToFacultyList(physics);
        universityForActivation.addNewFacultyToFacultyList(law);
        universityForActivation.addNewFacultyToFacultyList(economics);

        Group physicsFirstYearOfStudyGroup = new Group(112);
        Group physicsThirdYearOfStudyGroup = new Group(312);
        Group lawSecondYearOfStudyGroup = new Group(222);
        Group economicsFourthYearOfStudyGroup = new Group(444);

        physics.addNewGroupToGroupList(physicsFirstYearOfStudyGroup);
        physics.addNewGroupToGroupList(physicsThirdYearOfStudyGroup);
        law.addNewGroupToGroupList(lawSecondYearOfStudyGroup);
        economics.addNewGroupToGroupList(economicsFourthYearOfStudyGroup);

        Student ivanLapin = new Student("Ivan Lapin");
        Student kirillChernov = new Student("Kirill Chernov");
        Student daryaRomanova = new Student("Darya Romanova");
        Student egorPopov = new Student("Egor Popov");
        Student olgaUchaeva = new Student("Olga Uchaeva");
        Student borisEkkelev = new Student("Boris Ekkelev");
        Student mariaKadina = new Student("Maria Kadina");
        Student sergeyGurin = new Student("Sergey Gurin");
        Student dmitryBykov = new Student("Dmitry Bykov");

        physicsFirstYearOfStudyGroup.addNewStudentToStudentListOfGroup(ivanLapin);
        physicsFirstYearOfStudyGroup.addNewStudentToStudentListOfGroup(kirillChernov);
        physicsThirdYearOfStudyGroup.addNewStudentToStudentListOfGroup(daryaRomanova);
        physicsThirdYearOfStudyGroup.addNewStudentToStudentListOfGroup(egorPopov);
        lawSecondYearOfStudyGroup.addNewStudentToStudentListOfGroup(olgaUchaeva);
        lawSecondYearOfStudyGroup.addNewStudentToStudentListOfGroup(borisEkkelev);
        economicsFourthYearOfStudyGroup.addNewStudentToStudentListOfGroup(mariaKadina);
        economicsFourthYearOfStudyGroup.addNewStudentToStudentListOfGroup(sergeyGurin);
        economicsFourthYearOfStudyGroup.addNewStudentToStudentListOfGroup(dmitryBykov);

        try {
            ivanLapin.addNewCourseAndGradeToStudentRegister(Course.ENGLISH, 8);
            ivanLapin.addNewCourseAndGradeToStudentRegister(Course.PHYSICS, 9);
            kirillChernov.addNewCourseAndGradeToStudentRegister(Course.ENGLISH, 7);
            kirillChernov.addNewCourseAndGradeToStudentRegister(Course.PHYSICS, 9);
            daryaRomanova.addNewCourseAndGradeToStudentRegister(Course.ENGLISH, 6);
            daryaRomanova.addNewCourseAndGradeToStudentRegister(Course.PHYSICS, 8);
            egorPopov.addNewCourseAndGradeToStudentRegister(Course.ENGLISH, 5);
            egorPopov.addNewCourseAndGradeToStudentRegister(Course.PHYSICS, 7);
            olgaUchaeva.addNewCourseAndGradeToStudentRegister(Course.ENGLISH, 6);
            olgaUchaeva.addNewCourseAndGradeToStudentRegister(Course.SOCIOLOGY, 10);
            borisEkkelev.addNewCourseAndGradeToStudentRegister(Course.ENGLISH, 10);
            borisEkkelev.addNewCourseAndGradeToStudentRegister(Course.SOCIOLOGY, 8);
            mariaKadina.addNewCourseAndGradeToStudentRegister(Course.ENGLISH, 10);
            mariaKadina.addNewCourseAndGradeToStudentRegister(Course.PSYCHOLOGY, 9);
            mariaKadina.addNewCourseAndGradeToStudentRegister(Course.MATHEMATICS, 9);
            sergeyGurin.addNewCourseAndGradeToStudentRegister(Course.ENGLISH, 6);
            sergeyGurin.addNewCourseAndGradeToStudentRegister(Course.MATHEMATICS, 6);
            dmitryBykov.addNewCourseAndGradeToStudentRegister(Course.ENGLISH, 8);
            dmitryBykov.addNewCourseAndGradeToStudentRegister(Course.MATHEMATICS, 7);
        } catch (IllegalGradeException exception) {
            exception.printStackTrace();
        }
        return universityForActivation;
    }
}
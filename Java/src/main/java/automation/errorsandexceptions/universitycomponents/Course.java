package automation.errorsandexceptions.universitycomponents;

public enum Course {
    MATHEMATICS("Mathematics"),
    ENGLISH("English"),
    SOCIOLOGY("Sociology"),
    PHYSICS("Physics"),
    PSYCHOLOGY("Psychology");

    private String nameOfCourse;

    Course(String nameOfCourse) {
        if (nameOfCourse != null && !nameOfCourse.isEmpty()) {
            this.nameOfCourse = nameOfCourse;
        } else {
            System.out.println("\033[0;91mIncorrect course name: null or empty.\033[0m");
            System.exit(1);
        }
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    @Override
    public String toString() {
        return getNameOfCourse();
    }
}
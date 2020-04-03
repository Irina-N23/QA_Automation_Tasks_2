package automation.errorsandexceptions.universitycomponents;

public enum Course {
    MATHEMATICS("Mathematics"),
    ENGLISH("English"),
    SOCIOLOGY("Sociology"),
    PHYSICS("Physics"),
    PSYCHOLOGY("Psychology");

    private String nameOfCourse;

    Course(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    @Override
    public String toString() {
        return getNameOfCourse();
    }
}
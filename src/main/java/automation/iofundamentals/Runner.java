package automation.iofundamentals;

public class Runner {
    public static void main(String[] args) {
        Menu menu = new Menu();

        menu.printStartMessage();
        menu.validatePath();
        menu.restartOrExit();
    }
}
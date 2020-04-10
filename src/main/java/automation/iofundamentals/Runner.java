package automation.iofundamentals;

public class Runner {
    public static void main(String[] args) {
        Menu menu = new Menu();

        menu.validatePath(args[0]);
        menu.restartOrExit();
    }
}
package automation.iofundamentals;

import java.io.File;
import java.util.Scanner;

class Menu {
    private Scanner scanner = new Scanner(System.in);
    private DirectoryTreeWriter treeWriter = new DirectoryTreeWriter();
    private DirectoryTreeFileAnalyser fileAnalyser = new DirectoryTreeFileAnalyser();

    void printStartMessage() {
        System.out.println("Just input the path of any directory you want "
                           + "in order to create a text-file with its structure.\n"
                           + "You can also input the path of text-file with a directory tree "
                           + "if it exists.");
    }

    void validatePath() {
        File chosenFile = new File(scanner.nextLine());

        if (chosenFile.exists()) {
            if (chosenFile.isDirectory()) {
                treeWriter.writeTextFileWithDirectoryTree(chosenFile);
            } else if (chosenFile.getName().endsWith(".txt")) {
                System.out.printf("\nThe text-file \"" + chosenFile.getName() + "\" includes --> "
                                  +"%d directories and %d files.\n", fileAnalyser
                                  .getNumberOfDirectories(), fileAnalyser.getNumberOfFiles());
                System.out.printf("The average number of files in a directory: %.2f.\n",
                                  fileAnalyser.getAverageNumberOfFilesInDirectory());
                System.out.printf("The average file name length: %.2f.\n",
                                  fileAnalyser.getAverageFileNameLength());
            } else {
                System.out.print("\n\"" + chosenFile.getName()
                                 + "\" is not a directory or .txt-file.\n"
                                 + "Just input another path here: ");
                validatePath();
            }
        } else {
            System.out.print("\n\"" + chosenFile.getName() + "\" has not been found.\n"
                             + "Just input another path here: ");
            validatePath();
        }
    }

    private Scanner choice = new Scanner(System.in);

    void restartOrExit() {
        System.out.print("\nDo you want to run this program once again?\n"
                         + "Just input <yes>/<no> here:\t");

        switch (choice.next().toLowerCase()) {
            case "yes":
                System.out.println();
                printStartMessage();
                validatePath();
                deleteTextFileOrLeaveIt();
                choice.close();
                break;
            case "no":
                deleteTextFileOrLeaveIt();
                choice.close();
                scanner.close();
                break;
            default:
                System.out.println("\nYou have inputted incorrect data. Just try again!");
                restartOrExit();
        }
    }

    void deleteTextFileOrLeaveIt() {
        if (DirectoryTreeWriter.directoryTree.exists()) {
            System.out.print("\nDo you want to delete created text file before exit?"
                               + "\nJust input <yes>/<no> here:\t");

            switch (choice.next().toLowerCase()) {
                case "yes":
                    System.out.print("\nDeleting the file...");
                    System.out.print("\nCompleting the program...");
                    DirectoryTreeWriter.directoryTree.deleteOnExit();
                    choice.close();
                    break;
                case "no":
                    System.out.print("\nOK!\nCompleting the program...");
                    break;
                default:
                    System.out.println("\nYou have inputted incorrect data. Just try again!");
                    deleteTextFileOrLeaveIt();
            }
        }
    }
}
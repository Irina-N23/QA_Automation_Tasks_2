package automation.iofundamentals;

import java.io.File;
import java.util.Scanner;

class Menu {
    private DirectoryTreeWriter treeWriter = new DirectoryTreeWriter();
    private DirectoryTreeFileAnalyser fileAnalyser = new DirectoryTreeFileAnalyser();
    private Scanner choice = new Scanner(System.in);

    void validatePath(String path) {
        File chosenItem = new File(path);

        if (chosenItem.exists()) {
            if (chosenItem.isDirectory()) {
                if (DirectoryTreeWriter.directoryTree.exists()) {
                    treeWriter.treeContent.delete(0, treeWriter.treeContent.length());
                }
                treeWriter.writeTextFileWithDirectoryTree(chosenItem);
            } else if (chosenItem.getName().equals("DirectoryTree.txt")) {
                System.out.printf("\nThe text-file \"" + chosenItem.getName() + "\" includes --> "
                                  +"%d directories and %d files.\n", fileAnalyser
                                  .getNumberOfDirectories(), fileAnalyser.getNumberOfFiles());
                System.out.printf("The average number of files in a directory: %.2f.\n",
                                  fileAnalyser.getAverageNumberOfFilesInDirectory());
                System.out.printf("The average file name length: %.2f.\n",
                                  fileAnalyser.getAverageFileNameLength());
            } else {
                System.out.print("\n\"" + chosenItem.getName()
                                 + "\" is not a directory or file \"DirectoryTree.txt\".\n");
            }
        } else {
            System.out.print("\n\"" + chosenItem.getName() + "\" has not been found.\n");
        }
    }

    void restartOrExit() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nDo you want to run this program with a new path?\n"
                         + "Just input <yes>/<no> here:\t");

        switch (choice.next().toLowerCase()) {
            case "yes":
                System.out.print("\nJust input a path of any directory you want "
                                 + "in order to create a text file \"DirectoryTree.txt\" "
                                 + "with its structure!\nYou can also input "
                                 + "the path of the file \"DirectoryTree.txt\" if it exists.\n"
                                 + "Your choice:\t");
                validatePath(scanner.nextLine());
                restartOrExit();
                deleteTextFileOrLeaveIt();
                break;
            case "no":
                deleteTextFileOrLeaveIt();
                scanner.close();
                choice.close();
                System.exit(1);
                break;
            default:
                System.out.println("\nYou have inputted incorrect data. Just try again!");
                restartOrExit();
        }
    }

    private void deleteTextFileOrLeaveIt() {
        if (DirectoryTreeWriter.directoryTree.exists()) {
            System.out.print("\nDo you want to delete the file \"DirectoryTree.txt\" before exit?"
                             + "\nJust input <yes>/<no> here:\t");

            switch (choice.next().toLowerCase()) {
                case "yes":
                    System.out.print("\nDeleting the file...");
                    System.out.print("\nCompleting the program...");
                    DirectoryTreeWriter.directoryTree.deleteOnExit();
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
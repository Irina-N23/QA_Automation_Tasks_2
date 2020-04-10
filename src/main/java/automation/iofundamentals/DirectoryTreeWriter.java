package automation.iofundamentals;

import java.io.*;

class DirectoryTreeWriter {
    static String fileTab = "|    ";
    static String directoryTab = "|----";
    private StringBuilder treeContent = new StringBuilder();
    static File directoryTree = new File("src" + File.separator + "main" + File.separator + "java"
                                         + File.separator + "automation" + File.separator + "io"
                                         + "fundamentals" + File.separator + "DirectoryTree.txt");

    private StringBuilder getDirectoryTree(File directory, int depthLevel) {
        if (depthLevel == 0) {
            treeContent.append(directory.getName()).append("\n");
        }

        for (File item : directory.listFiles()) {
            if (item.isFile()) {
                for (int i = 0; i <= depthLevel; i++) {
                    treeContent.append(fileTab);
                }
                treeContent.append(item.getName()).append("\n");
            } else if (item.isDirectory()) {
                for (int i = 0; i <= depthLevel; i++) {
                    treeContent.append(directoryTab);
                }
                treeContent.append("[").append(item.getName()).append("]\n");
                getDirectoryTree(item, depthLevel + 1);
            }
        }
        return treeContent;
    }

    void writeTextFileWithDirectoryTree(File chosenDirectory) {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(
                                                           new FileWriter(directoryTree)))) {
            printWriter.print(getDirectoryTree(chosenDirectory, 0).toString());
            printWriter.flush();
            System.out.println("File \"DirectoryTree.txt\" has been successfully written.");
        } catch (IOException exception) {
            System.err.println("File writing has been failed:\n" + exception);
        }
    }
}
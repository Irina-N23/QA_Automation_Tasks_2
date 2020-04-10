package automation.iofundamentals;

import java.io.*;

class DirectoryTreeFileAnalyser {
    private int totalNumberOfDirectories;
    private int totalNumberOfFiles;

    void readTextFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DirectoryTreeWriter
                                                                               .directoryTree))) {
            bufferedReader.lines().forEach(System.out::println);
        } catch (IOException exception) {
            System.err.print("File reading has been failed:\n" + exception);
        }
    }

    int getNumberOfDirectories() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DirectoryTreeWriter
                                                                               .directoryTree))) {
            String line;
            totalNumberOfDirectories = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(DirectoryTreeWriter.directoryTab)) {
                    totalNumberOfDirectories++;
                }
            }
        } catch (IOException exception) {
            System.err.print("File reading has been failed:\n" + exception);
        }
        return totalNumberOfDirectories;
    }

    int getNumberOfFiles() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DirectoryTreeWriter
                                                                               .directoryTree))) {
            String line;
            totalNumberOfFiles = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(DirectoryTreeWriter.fileTab)) {
                    totalNumberOfFiles++;
                }
            }
        } catch (IOException exception) {
            System.err.print("File reading has been failed:\n" + exception);
        }
        return totalNumberOfFiles;
    }

    double getAverageNumberOfFilesInDirectory() {
        totalNumberOfDirectories = 0;
        totalNumberOfFiles = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DirectoryTreeWriter
                                                                               .directoryTree))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(DirectoryTreeWriter.directoryTab)) {
                    totalNumberOfDirectories++;
                }
                if (line.contains(DirectoryTreeWriter.fileTab)) {
                    totalNumberOfFiles++;
                }
            }
        } catch (IOException exception) {
            System.err.print("File reading has been failed:\n" + exception);
        }
        return (totalNumberOfDirectories == 0) ? totalNumberOfFiles :
                                             totalNumberOfFiles / (double) totalNumberOfDirectories;
    }

    double getAverageFileNameLength() {
        totalNumberOfFiles = 0;
        double totalFileNameLength = 0.0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DirectoryTreeWriter
                                                                               .directoryTree))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.matches(("^(\\|(\\s{4}))+(.+)(\\.\\w{2,4}\\d*)$"))) {
                    String changedLine = (line.replaceAll("^(\\|(\\s{4}))+", "")
                                              .replaceAll("(\\.\\w{2,4}\\d*)$", ""));
                    totalNumberOfFiles++;
                    totalFileNameLength += changedLine.length();
                }
            }
        } catch (IOException exception) {
            System.err.print("File reading has been failed:\n" + exception);
        }
        return (totalNumberOfFiles == 0) ? totalNumberOfFiles :
                                             totalNumberOfFiles / (double) totalNumberOfDirectories;
    }
}
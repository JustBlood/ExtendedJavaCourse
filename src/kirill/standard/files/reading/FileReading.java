package kirill.standard.files.reading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class FileReading {
    public static void main(String[] args) {
        String path = getPath("src", "BaseJava", "FileReading", "1");
        File file = new File(path);
        printFileContent(file);
        writeToFile(file, "Omg я пишу в файлик");
        printFileContent(file);
    }

    public static void writeToFile(File file, String message) {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(message);
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    public static void printFileContent(File file) {
        try (Scanner sc = new Scanner(file)){
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    public static String getPath(String... directoriesPath) {
        StringBuilder path = new StringBuilder();
        String separator = File.separator;
        if (Objects.isNull(directoriesPath) || directoriesPath.length == 0)
        {
            throw new IllegalArgumentException("Путь не должен быть пустым");
        }
        for (int i = 0; i < directoriesPath.length; i++) {
            path.append(directoriesPath[i]);
            if (i != directoriesPath.length - 1) {
                path.append(separator);
            }
        }
        return path.toString();
    }
}

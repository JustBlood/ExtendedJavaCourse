package kirill.standard.files.serialization.secondEx;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Deserialization {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        Path path = Path.of("src", "kirill", "standard", "files", "serialization", "secondEx");
        File file = new File(path.toString());

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file.getPath() + "\\employeesList.bin")))
        {
            employeeList = (ArrayList<Employee>) ois.readObject();
            employeeList.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file.getPath() + "\\employees.bin")))
        {
            while (true) {
                Employee empl = (Employee) ois.readObject();
                System.out.println("Сотрудник: " + empl);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }
}

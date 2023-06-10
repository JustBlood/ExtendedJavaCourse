package kirill.standard.files.serialization.secondEx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Serialization {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Kirill", 150000, 20);
        Employee emp2 = new Employee("Elena", 30000, 50);
        Employee emp3 = new Employee("Sergey", 20000, 45);
        Employee emp4 = new Employee("Irina", 90000, 20);
        Employee emp5 = new Employee("Andrey", 90000, 19);
//        Employee emp1 = new Employee("Kirill", 150000);
//        Employee emp2 = new Employee("Elena", 30000);
//        Employee emp3 = new Employee("Sergey", 20000);
//        Employee emp4 = new Employee("Irina", 90000);
//        Employee emp5 = new Employee("Andrey", 90000);

        List<Employee> employees = new ArrayList<>(5);
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);

        Path path = Path.of("src", "kirill", "standard", "files", "serialization", "secondEx");
        File file = new File(path.toString());
        try(ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(file.getPath() + "\\employeesList.bin")
        )) {
            outputStream.writeObject(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(file.getPath() + "\\employees.bin")
        )) {
            for (Employee employee : employees) {
                outputStream.writeObject(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done!");
    }
}

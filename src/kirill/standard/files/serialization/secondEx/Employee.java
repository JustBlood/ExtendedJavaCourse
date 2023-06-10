package kirill.standard.files.serialization.secondEx;

import java.io.Serializable;

public class Employee implements Serializable {
    static final long serialVersionUID = 2;
    private static int ID;
    private String name;
    private final int id;
    private transient int salary;
    private int age;

    public Employee(String name,
            int salary,
            int age) {
        this.name = name;
        this.salary = salary;
        this.id = ID++;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}

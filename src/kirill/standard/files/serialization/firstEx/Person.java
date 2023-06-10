package kirill.standard.files.serialization.firstEx;

import java.io.Serializable;

public class Person implements Serializable {
    private static int idCounter = 0;

    public Person(String name) {
        this.name = name;
        this.id = idCounter++;
    }

    private String name;
    private final int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", id=" + id +
                '}';
    }
}

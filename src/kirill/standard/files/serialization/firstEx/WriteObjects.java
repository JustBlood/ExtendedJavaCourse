package kirill.standard.files.serialization.firstEx;

import kirill.standard.files.reading.FileReading;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObjects {
    public static void main(String[] args) {
        Person[] persons = {
                new Person("Tom"),
                new Person("Donald"),
                new Person("Garold")
        };

        String path = FileReading.getPath("src", "BaseJava", "Serialization", "peoples.bin");
        try (FileOutputStream fos = new FileOutputStream(path)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

//            oos.writeInt(persons.length);

            oos.writeObject(persons);
//            for (var person : persons) {
//                oos.writeObject(person);
//            }

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

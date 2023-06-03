package kirill.standard.files.serialization;

import kirill.standard.files.reading.FileReading;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObject {
    public static void main(String[] args) {
        Person person1 = new Person("Кирилл");
        Person person2 = new Person("Даня");
        Person person3 = new Person("Толя");
        String path = FileReading.getPath("src", "BaseJava", "Serialization", "people.bin");
        try (FileOutputStream fos = new FileOutputStream(path)){
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(person1);
            oos.writeObject(person2);
            oos.writeObject(person3);

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

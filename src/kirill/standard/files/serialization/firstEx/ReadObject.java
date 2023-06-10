package kirill.standard.files.serialization.firstEx;

import kirill.standard.files.reading.FileReading;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadObject {
    public static void main(String[] args) {
        String path = FileReading.getPath("src", "BaseJava", "Serialization", "people.bin");
        try (FileInputStream fos = new FileInputStream(path)){
            ObjectInputStream ois = new ObjectInputStream(fos);
            Person person1 = (Person) ois.readObject();
            Person person2 = (Person) ois.readObject();
            Person person3 = (Person) ois.readObject();
            ois.close();

            System.out.println(person1);
            System.out.println(person2);
            System.out.println(person3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

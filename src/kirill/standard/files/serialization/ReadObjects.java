package kirill.standard.files.serialization;

import kirill.standard.files.reading.FileReading;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class ReadObjects {
    public static void main(String[] args) {
        String path = FileReading.getPath("src", "BaseJava", "Serialization", "peoples.bin");
        try (FileInputStream fis = new FileInputStream(path)) {
            ObjectInputStream ois = new ObjectInputStream(fis);

//            int length = ois.readInt();
//            Person[] persons = new Person[length];
//            for (int i = 0; i < length; i++) {
//                persons[i] = (Person) ois.readObject();
//            }
            Person[] persons = (Person[]) ois.readObject();

            System.out.println(Arrays.toString(persons));

            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

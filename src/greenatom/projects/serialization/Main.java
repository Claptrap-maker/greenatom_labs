package greenatom.projects.serialization;

import java.io.*;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        final Human human = new Human("Julia", 2, null);
        /* Запись объекта во временный файл, который удалится системой */
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(human);
        }
        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final Human contactFromFile = (Human) ois.readObject();
            System.out.println(contactFromFile);
        }
    }
}

package greenatom.projects.serialization;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class Human implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private transient String occupation;

    public Human(String name, int age, String occupation) {
        this.name = name;
        this.age = age;
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "Human{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", occupation='" + occupation + '\''
                + '}';
    }

    private void readObject(ObjectInputStream ois) throws Exception {
        // performing default deserialization of Account object
        ois.defaultReadObject();

        // deserializing the occupation from the file
        if (this.age >= 0 && this.age < 3) {
            this.occupation = "Sits home";
        } else if (this.age >= 3 && this.age < 7) {
            this.occupation = "Goes to kindergarten";
        } else if (this.age >= 7 && this.age < 18) {
            this.occupation = "Pupil";
        } else if (this.age >= 18 && this.age < 24) {
            this.occupation = "Student";
        } else if (this.age >= 24 && this.age < 65) {
            this.occupation = "Worker";
        } else if (this.age >= 65) {
            this.occupation = "Retired";
        }
    }
}

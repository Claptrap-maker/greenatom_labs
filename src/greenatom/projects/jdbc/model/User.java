package greenatom.projects.jdbc.model;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    private int id;
    private String username;
    private LocalDate lDate;
    private int age;

    private String email;

    public User() {
    }

    public User(String username, LocalDate lDate, int age, String email) {
        this.username = username;
        this.lDate = lDate;
        this.email = email;
        this.age = age;
    }

    public User(int id, String username, LocalDate lDate, int age, String email) {
        this.id = id;
        this.username = username;
        this.lDate = lDate;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getlDate() {
        return lDate;
    }

    public void setlDate(LocalDate lDate) {
        this.lDate = lDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", username='" + username + '\''
                + ", lDate=" + lDate
                + ", age=" + age
                + ", email='" + email + '\''
                + '}';
    }
}

package greenatom.projects.jdbc.and.hibernate;

import greenatom.projects.jdbc.and.hibernate.model.User;
import greenatom.projects.jdbc.and.hibernate.service.UserService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        UserService userService = new UserService();

        //test insert
        insertUser(userService);

        //test getAll
        getAll(userService);

        //test update
        updateUser(userService);

        //test getUser
        getUser(userService);

        //test delete
        deleteUser(userService);

        //final test getAll
        getAll(userService);

    }

    public static void insertUser(UserService userService) {
        User user = new User(
                "Fiona",
                LocalDate.parse("1990-06-06", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                33,
                "fiona@mail.com");
        userService.insertUser(user);
    }

    public static void updateUser(UserService userService) {
        Optional<User> user = userService.getAll()
                .stream()
                .filter(user1 -> user1.getId() == 5)
                .findFirst();
        String[] params = {
                "Fiona Tiny",
                "1990-07-07",
                "34",
                "fiona123@mail.com",
                "2"};
        userService.updateUser(user.get(), params);
    }

    public static void deleteUser(UserService userService) {
        Optional<User> user = userService.getAll()
                .stream()
                .filter(user1 -> user1.getId() == 5)
                .findFirst();
        userService.deleteUser(user.get());
    }

    public static void getUser(UserService userService) {
        int id = 5;
        userService.getUser(id);
    }

    public static void getAll(UserService userService) {
        List<User> users = userService.getAll();
    }

}

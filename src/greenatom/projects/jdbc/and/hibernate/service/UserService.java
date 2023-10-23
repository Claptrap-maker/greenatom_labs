package greenatom.projects.jdbc.and.hibernate.service;

import greenatom.projects.jdbc.and.hibernate.dao.IUserDao;
import greenatom.projects.jdbc.and.hibernate.dao.UserDao;
import greenatom.projects.jdbc.and.hibernate.model.User;

import java.util.List;

public class UserService {
    private IUserDao userDao = new UserDao();

    public void insertUser(User user) {
        System.out.println("Inserting user");
        int generatedId = userDao.insert(user);

        if (generatedId != -1) {
            System.out.println("Successfully inserted user with id of " + user.getId());
        }
    }

    public void updateUser(User user, String[] params) {
        System.out.println("Updating user");
        int updatedId = userDao.update(user, params);
        if (updatedId != -1) {
            System.out.println("Successfully updated user with id of " + user.getId());
        }
    }

    public void deleteUser(User user) {
        System.out.println("Deleting user");
        int deletedId = userDao.delete(user);
        if (deletedId != -1) {
            System.out.println("Successfully deleted user with id of " + user.getId());
        }
    }

    public User getUser(int id) {
        System.out.println("Getting user");
        User user = userDao.getUser(id);
        if (user != null) {
            System.out.println("Successfully got user with id of " + user.getId());
        }
        return user;
    }

    public List<User> getAll() {
        System.out.println("Getting user");
        return userDao.getAll();
    }


}

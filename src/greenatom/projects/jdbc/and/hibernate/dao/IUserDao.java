package greenatom.projects.jdbc.and.hibernate.dao;

import greenatom.projects.jdbc.and.hibernate.model.User;

import java.util.List;

public interface IUserDao {
    int insert(User user);
    int update(User user, String[] params);
    int delete(User user);
    User getUser(int id);
    List<User> getAll();
}

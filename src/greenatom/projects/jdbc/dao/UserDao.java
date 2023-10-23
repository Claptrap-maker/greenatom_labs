package greenatom.projects.jdbc.dao;

import greenatom.projects.jdbc.model.User;
import greenatom.projects.jdbc.util.ConnectionUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {

    private Connection connection = ConnectionUtil.getConnection();

    private List<User> users = new ArrayList<>();

    public UserDao() {
        getAll();
    }

    @Override
    public int insert(User user) {
        String sql = "insert into \"user\" (user_name, birthdate, age, email) values (?, ?, ?, ?) "
                + "returning \"user\".user_id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setDate(2, Date.valueOf(user.getlDate()));
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getEmail());

            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {

                rs.next();

                int id = rs.getInt(1);

                System.out.println("We returned user with id №" + id);

                user.setId(id);

                users.add(user);

                return id;
            }

            return -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user, String[] params) {
        String sql = "update \"user\" set user_name = ?, birthdate = ?, age = ?, email = ? "
                + "where \"user\".user_id = ? returning \"user\".user_id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, params[0]);
            ps.setDate(2, Date.valueOf(params[1]));
            ps.setInt(3, Integer.parseInt(params[2]));
            ps.setString(4, params[3]);
            ps.setInt(5, user.getId());

            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {

                rs.next();

                int id = rs.getInt(1);

                System.out.println("We updated user with id №" + id);

                user.setUsername(params[0]);
                user.setlDate(LocalDate.parse(params[1], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                user.setAge(Integer.parseInt(params[2]));
                user.setEmail(params[3]);

                return id;
            }

            return -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(User user) {
        String sql = "delete from \"user\" where \"user\".user_id = ? returning \"user\".user_id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user.getId());

            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {

                rs.next();

                int id = rs.getInt(1);

                System.out.println("We deleted user with id №" + id);

                users.remove(user);

                return id;

            }

            return -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(int id) {
        String sql = "select user_name, birthdate, age, email from \"user\" where \"user\".user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {

                rs.next();

                System.out.println("We get user with username " + rs.getString("user_name") + ", birthdate "
                        + new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("birthdate"))
                        + ", age " + rs.getInt("age") + ", email " + rs.getString("email"));

                for (User user : users) {
                    if (user.getId() == id) {
                        return user;
                    }
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<User> getAll() {

        String sql = "select * from \"user\" ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (!users.isEmpty()) {
                users.clear();
            }

            while (rs.next()) {
                System.out.println("id: " + rs.getInt("user_id")
                        + "\t user_name: " + rs.getString("user_name")
                        + "\t birthdate: " + rs.getDate("birthdate").toLocalDate()
                        + "\t age: " + rs.getInt("age")
                        + "\t email: " + rs.getString("email"));

                users.add(new User(
                            rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getDate("birthdate").toLocalDate(),
                            rs.getInt("age"),
                            rs.getString("email")));
            }

            return users;

            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

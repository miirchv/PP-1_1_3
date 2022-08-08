package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    private Util newU = new Util();
    private Connection connection = newU.getConnection();



    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS users1" + "(id INTEGER not NULL AUTO_INCREMENT, " + " name VARCHAR(40), " + " lastName VARCHAR(40), " + " age INTEGER, " + " PRIMARY KEY ( id ))");
        } catch (SQLException e) {

        } finally {
            try {
                connection.close();

            } catch (SQLException e) {

            }
        }

    }

    public void dropUsersTable() {
        try {
            connection.createStatement().executeUpdate("DROP TABLE IF EXISTS users1");
        } catch (Exception e) {

        } finally {
            try {
                connection.close();

            } catch (SQLException e) {

            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users1 (name, lastName, age) values (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();

            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {

        } finally {
            try {
                connection.close();

            } catch (SQLException e) {

            }
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users1 where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {

        } finally {
            try {
                connection.close();

            } catch (SQLException e) {

            }
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from users1");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge( resultSet.getByte("age"));
                list.add(user);
            }
            return list;

        } catch (Exception e) {

        } finally {
            try {
                connection.close();

            } catch (SQLException e) {

            }
        }

        return list;
    }

    public void cleanUsersTable() {
        try {
            connection.prepareStatement("delete from users1").executeUpdate();
        } catch (Exception e) {

        } finally {
            try {
                connection.close();

            } catch (SQLException e) {

            }
        }
    }
}

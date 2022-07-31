package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    private Util newU = new Util();
    private Connection connection = newU.getConnection();



    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS users1" + "(id INTEGER not NULL AUTO_INCREMENT, " + " name VARCHAR(40), " + " lastName VARCHAR(40), " + " age INTEGER, " + " PRIMARY KEY ( id ))");
        } catch (SQLException e) {

        } finally {
            connection.close();
        }

    }

    public void dropUsersTable() throws SQLException {
        try {
            connection.createStatement().executeUpdate("DROP TABLE IF EXISTS users1");
        } catch (Exception e) {

        } finally {
            connection.close();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try {
            connection.prepareStatement("insert into users1 (name, lastName, age) values ( '" + name + "', '" + lastName + "', '" + age + "')").executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {

        } finally {
            connection.close();
        }
    }

    public void removeUserById(long id) throws SQLException {
        try {
            connection.prepareStatement("delete from users1 where id = " + id).executeUpdate();
        } catch (Exception e) {

        } finally {
            connection.close();
        }
    }

    public List<User> getAllUsers() throws SQLException {
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
            connection.close();
        }

        return list;
    }

    public void cleanUsersTable () throws SQLException {
        try {
            connection.prepareStatement("delete from users1").executeUpdate();
        } catch (Exception e) {

        } finally {
            connection.close();
        }
    }
}

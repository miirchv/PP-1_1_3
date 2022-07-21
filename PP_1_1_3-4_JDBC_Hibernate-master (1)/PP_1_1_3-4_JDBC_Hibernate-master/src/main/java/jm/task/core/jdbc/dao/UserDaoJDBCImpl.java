package jm.task.core.jdbc.dao;

import com.sun.jdi.connect.spi.Connection;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        try {
            Util newU = new Util();
            Statement statement = newU.getConnection().createStatement();
            String newTable = "CREATE TABLE users1" + "(id INTEGER not NULL AUTO_INCREMENT, " + " name VARCHAR(40), " + " lastName VARCHAR(40), " + " age INTEGER, " + " PRIMARY KEY ( id ))";
            statement.executeUpdate(newTable);
        } catch (SQLException e) {

        }

    }

    public void dropUsersTable() {
        try {
            Util newU = new Util();
            Statement statement = newU.getConnection().createStatement();
            String dropTable = "DROP TABLE users1";
            statement.executeUpdate(dropTable);
        } catch (Exception e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Util newU = new Util();
            Statement statement = newU.getConnection().createStatement();
            String newUser = "insert into users1 (name, lastName, age) values ( '" + name + "', '" + lastName + "', '" + age + "')";
            statement.executeUpdate(newUser);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            statement.close();
        } catch (Exception e) {

        }
    }

    public void removeUserById(long id) throws SQLException {
        try {
            Util newU = new Util();
            Statement statement = newU.getConnection().createStatement();
            String removeUser = "delete from users1 where id = " + id;
            statement.executeUpdate(removeUser);
        } catch (SQLException e) {

        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try {
            Util newU = new Util();
            Statement statement = newU.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users1");

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

        }

        return list;
    }

    public void cleanUsersTable () {
        try {
            Util newU = new Util();
            Statement statement = newU.getConnection().createStatement();
            String cleanTable = "delete from users1";
            statement.executeUpdate(cleanTable);
        } catch (Exception e) {

        }
    }
}

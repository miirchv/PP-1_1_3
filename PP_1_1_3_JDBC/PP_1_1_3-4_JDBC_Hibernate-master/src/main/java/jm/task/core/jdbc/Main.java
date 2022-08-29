package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь


        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();

        userDaoJDBC.saveUser("Ivan", "Ivanov", (byte) 20);
        userDaoJDBC.saveUser("Petr", "Petrov", (byte) 22);
        userDaoJDBC.saveUser("Alex", "Alexov", (byte) 25);
        userDaoJDBC.saveUser("Nikolay", "Nikolaev", (byte) 30);

        List<User> allUsers = userDaoJDBC.getAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            System.out.println(allUsers.get(i));
        }

        userDaoJDBC.cleanUsersTable();

       userDaoJDBC.dropUsersTable();
    }
}

package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection conn;
    public UserDaoJDBCImpl() {
        conn =  Util.getConnect();
    }

    public void createUsersTable() {
        try (Statement st = conn.createStatement()) {
            //st = conn.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS User (id MEDIUMINT NOT NULL AUTO_INCREMENT" +
                    ", name VARCHAR(30) NOT NULL, " +
                    "lastName VARCHAR(30) NOT NULL, age INT NOT NULL, PRIMARY KEY (id));");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement st = conn.createStatement()) {
            //st = conn.createStatement();
            st.executeUpdate("DROP TABLE IF EXISTS User");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String sql = "INSERT INTO User (name, lastName, age) Values (?, ?, ?)";
            PreparedStatement preparedStatement = Util.getConnect().prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception t) {
            t.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            String sql = "DELETE FROM User WHERE Id = ?";
            PreparedStatement preparedStatement = Util.getConnect().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ResultSet resultSet = null;
        List<User> list = new ArrayList<>();
        try (Statement st = conn.createStatement()) {
            //st = conn.createStatement();
            resultSet = st.executeQuery("SELECT * FROM User");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");
                list.add(new User(name, lastName, (byte) age));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement st = conn.createStatement()) {
            //st = conn.createStatement();
            st.executeUpdate("TRUNCATE TABLE User");
        } catch (SQLException t) {
            t.printStackTrace();
        }
    }
}

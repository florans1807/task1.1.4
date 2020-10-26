package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = Util.getConnect();

        UserService us = new UserServiceImpl();
        us.cleanUsersTable();
        us.saveUser("gbfv", "dc", (byte) 20);
        System.out.println("User с именем – gbfv добавлен в базу данных");
        us.saveUser("ccc", "dc", (byte) 21);
        System.out.println("User с именем – ccc добавлен в базу данных");
        us.saveUser("aaa", "bb", (byte) 22);
        System.out.println("User с именем – aaa добавлен в базу данных");
        us.saveUser("uuu", "ppp", (byte) 23);
        System.out.println("User с именем – uuu добавлен в базу данных");
        us.getAllUsers().forEach(s -> System.out.println(s.toString()));
        us.cleanUsersTable();
        us.dropUsersTable();
        try {
            connection.close();
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        }
    }
}

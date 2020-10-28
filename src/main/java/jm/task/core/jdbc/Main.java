package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Util.getConnect();
        UserService userService = new UserServiceImpl();
        userService.cleanUsersTable();
        userService.saveUser("gbfv", "dc", (byte) 20);
        System.out.println("User с именем – gbfv добавлен в базу данных");
        userService.saveUser("ccc", "dc", (byte) 21);
        System.out.println("User с именем – ccc добавлен в базу данных");
        userService.saveUser("aaa", "bb", (byte) 22);
        System.out.println("User с именем – aaa добавлен в базу данных");
        userService.saveUser("uuu", "ppp", (byte) 23);
        System.out.println("User с именем – uuu добавлен в базу данных");
        userService.getAllUsers().forEach(s -> System.out.println(s.toString()));
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    private static final UserServiceImpl userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("user1a", "userA", (byte) 10);
        userService.saveUser("user2b", "userB", (byte) 15);
        userService.saveUser("user3c", "userC", (byte) 20);
        userService.saveUser("user4dd", "userD", (byte) 25);

        for (int i = 0; i < 1; i++) {
            System.out.println(userService.getAllUsers());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeConnect();
    }
}

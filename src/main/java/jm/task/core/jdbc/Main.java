package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {

    private static final UserServiceImpl userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("user1a", "userA", (byte) 10);
        userService.saveUser("user2b", "userB", (byte) 15);
        userService.saveUser("user3c", "userC", (byte) 20);
        userService.saveUser("user4d", "userD", (byte) 25);

        List<User> users = userService.getAllUsers();
        for (User us : users) {
            System.out.println(us);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();


//        try {
//            Util.getSessionFactory().close();
//            System.out.println("Connection close");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}

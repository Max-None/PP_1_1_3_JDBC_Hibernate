package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Tony", "Tester", (byte) 25);
        userService.saveUser("Harry", "Hacker", (byte) 35);
        userService.saveUser("Cody", "Coder", (byte) 45);
        userService.saveUser("Johny", "Depp", (byte) 55);

        List<User> users = userService.getAllUsers();
        for(User user: users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

package ru.otus.web.helpers;

import ru.otus.api.model.User;
import ru.otus.api.service.DBServiceUser;

public class UsersHelper {

    private UsersHelper() {
    }

    public static void makeDefaultUsers(DBServiceUser dbServiceUser) {
        User adminUser = new User("Vasya", "vas", "123");
        dbServiceUser.saveUser(adminUser);
        User anotherUser = new User("Ivan", "ivan", "123");
        dbServiceUser.saveUser(anotherUser);
    }
}
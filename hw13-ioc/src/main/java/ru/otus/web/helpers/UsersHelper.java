package ru.otus.web.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.api.model.User;
import ru.otus.api.service.DBServiceUser;

@Component
public class UsersHelper {

    @Autowired
    private DBServiceUser dbServiceUser;

    public UsersHelper() {
    }

    public static void makeDefaultUsers(DBServiceUser dbServiceUser) {
        User adminUser = new User("Vasya", "vas", "123");
        dbServiceUser.saveUser(adminUser);
        User anotherUser = new User("Ivan", "ivan", "123");
        dbServiceUser.saveUser(anotherUser);
    }

    public void init() {
        makeDefaultUsers(dbServiceUser);
    }
}
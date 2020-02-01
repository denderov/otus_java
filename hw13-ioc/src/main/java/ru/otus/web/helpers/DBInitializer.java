package ru.otus.web.helpers;

import ru.otus.api.model.User;
import ru.otus.api.service.DBServiceUser;

public class DBInitializer {

    private final DBServiceUser dbServiceUser;

    public DBInitializer(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    public void init() {
        User adminUser = new User("Vasya", "vas", "123");
        dbServiceUser.saveUser(adminUser);
        User anotherUser = new User("Ivan", "ivan", "123");
        dbServiceUser.saveUser(anotherUser);
    }
}
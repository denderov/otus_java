package ru.otus.api.service;

import org.springframework.stereotype.Service;
import ru.otus.api.dao.UserDao;
import ru.otus.api.model.User;
import ru.otus.cachehw.HwCache;
import ru.otus.web.helpers.UsersHelper;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class DbServiceUserCached extends DbServiceUserImpl {

    private final HwCache<Long, User> myCache;

    public DbServiceUserCached(UserDao userDao, HwCache<Long, User> cache) {
        super(userDao);
        this.myCache = cache;
    }

    @PostConstruct
    public void loadData() {
        UsersHelper.makeDefaultUsers(this);
    }

    @Override
    public long saveUser(User user) {
        long userId = super.saveUser(user);
        myCache.put(userId, user);
        return userId;
    }

    @Override
    public Optional<User> getUser(long id) {
        return Optional.of(myCache.get(id))
                .or(() -> super.getUser(id).map(userFromDb -> {
                    myCache.put(userFromDb.getId(), userFromDb);
                    return userFromDb;
                }));
    }
}


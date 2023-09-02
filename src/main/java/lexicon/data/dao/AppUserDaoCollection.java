package lexicon.data.dao;

import lexicon.data.impl.AppUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class AppUserDaoCollection implements AppUserDao {

    private Collection<AppUser> appUsers;

    private static AppUserDaoCollection instance;

    private AppUserDaoCollection() {
        this.appUsers = new ArrayList<>();
    }

    public static AppUserDaoCollection getInstance() {
        if (instance == null) {
            instance = new AppUserDaoCollection();
        }
        return instance;
    }

    @Override
    public AppUser persist(AppUser appUser) {
        appUsers.add(appUser);
        return appUser;
    }

    @Override
    public AppUser findByUsername(String username) {
        List<AppUser> appUsersAll = new ArrayList<>(findAll());
        for (AppUser appUser : appUsersAll) {
            if (username.equalsIgnoreCase(appUser.getUsername())) {
                return appUser;
            }
        }
        return null;
    }

    @Override
    public Collection<AppUser> findAll() {
        return appUsers;
    }

    @Override
    public void remove(String username) {
        appUsers.remove(findByUsername(username));
    }
}

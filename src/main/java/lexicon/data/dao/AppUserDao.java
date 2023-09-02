package lexicon.data.dao;

import lexicon.data.impl.AppUser;

import java.util.Collection;


public interface AppUserDao extends BaseDao<AppUser>{
    //    AppUser persist(AppUser appUser);
    AppUser findByUsername(String username);
    Collection<AppUser> findAll();
    void remove(String username);

}

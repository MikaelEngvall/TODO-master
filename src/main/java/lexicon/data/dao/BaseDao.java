package lexicon.data.dao;

import java.util.Optional;
public interface BaseDao<T> {

    T persist(T t);
}

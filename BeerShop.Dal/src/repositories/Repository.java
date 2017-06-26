package repositories;

import java.util.List;

/**
 * Created by mario on 18.5.2017..
 */
public interface Repository<T> {

    List<T> getAll();
    void create(T object);
    void update(T object);
    void delete(T object);
}

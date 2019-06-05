package despresso.persistence;

import java.util.List;

public interface RepositoryInterface<T> {
    List<T> getAll();
    T getByID(int ID);
    void insert(T item);
    void update(T item);
    void delete(T item);
}

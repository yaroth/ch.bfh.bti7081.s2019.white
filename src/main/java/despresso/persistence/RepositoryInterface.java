package despresso.persistence;

import java.util.List;

public interface RepositoryInterface<T> {
    List<T> readAll();
    T readByID(int ID);
    T create(T element);
    T updateByID(T element, int ID);
    T deleteByID(int ID)
}

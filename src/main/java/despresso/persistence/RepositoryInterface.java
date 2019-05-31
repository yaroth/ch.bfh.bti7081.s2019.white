package despresso.persistence;

import java.util.List;

public interface RepositoryInterface<T> {
    List<T> getAll();
    T getByID(int ID);
    T insertToDB(T element);
    T updateByID(T element, int ID);
    T deleteByID(int ID);
}

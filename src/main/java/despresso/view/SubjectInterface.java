package despresso.view;

import despresso.presenter.ObserverInterface;

public interface SubjectInterface<T> {

    void removeObserver(T observer);

    void addObserver(T observer);
}
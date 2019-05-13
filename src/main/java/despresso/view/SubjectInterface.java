package despresso.view;

import despresso.presenter.ObserverInterface;

public interface SubjectInterface {

    void removeObserver(ObserverInterface observer);

    void addObserver(ObserverInterface observer);
}
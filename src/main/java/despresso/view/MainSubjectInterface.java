package despresso.view;

import despresso.presenter.MainObserverInterface;

public interface MainSubjectInterface {

    void removeObserver(MainObserverInterface observer);

    void addObserver(MainObserverInterface observer);
}
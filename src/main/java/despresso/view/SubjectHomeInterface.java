package despresso.view;

import despresso.presenter.HomeObserverInterface;

public interface SubjectHomeInterface {
    void removeObserver(HomeObserverInterface observer);

    void addObserver(HomeObserverInterface observer);
}

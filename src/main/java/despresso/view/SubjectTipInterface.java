package despresso.view;

import despresso.presenter.ObserverInterface;
import despresso.presenter.TipObserverInterface;

public interface SubjectTipInterface {
    void removeObserver(TipObserverInterface observer);

    void addObserver(TipObserverInterface observer);
}

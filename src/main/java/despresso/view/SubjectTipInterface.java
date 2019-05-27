package despresso.view;

import despresso.logic.TipDuration;
import despresso.logic.TipLocation;
import despresso.logic.TipType;
import despresso.presenter.ObserverInterface;
import despresso.presenter.TipObserverInterface;

public interface SubjectTipInterface {
    void removeObserver(TipObserverInterface observer);

    void addObserver(TipObserverInterface observer);

    boolean getAnger();
    boolean getDisgust();
    boolean getAnxiety();
    boolean getSadness();
    boolean getFear();
    TipDuration getDuration();
    TipType getTipType();
    TipLocation getTipLocation();

    void clearAnger();
    void clearDisgust();
    void clearAnxiety();
    void clearSadness();
    void clearFear();
    void clearDuration();
    void clearType();
    void clearLocation();

}

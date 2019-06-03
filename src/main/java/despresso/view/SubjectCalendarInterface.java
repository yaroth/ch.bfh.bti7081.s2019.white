package despresso.view;

import despresso.presenter.ObserverInterface;
import despresso.presenter.CalendarObserverInterface;

public interface SubjectCalendarInterface {

    void removeObserver(CalendarObserverInterface observer);

    void addObserver(CalendarObserverInterface observer);
}

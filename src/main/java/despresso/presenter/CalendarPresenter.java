package despresso.presenter;


import despresso.logic.CalendarModel;
import despresso.view.CalendarViewImpl;

public class CalendarPresenter implements ObserverInterface {

    private CalendarModel model;
    private CalendarViewImpl view;

    public CalendarPresenter(CalendarModel model, CalendarViewImpl view) {
        this.model = model;
        this.view = view;
        this.view.addObserver(this);
    }

    @Override
    public void update(String actionName) {

    }
}

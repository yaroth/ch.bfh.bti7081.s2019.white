package despresso.presenter;


import despresso.logic.CalendarModel;
import despresso.view.CalendarViewImpl;

public class CalendarPresenter implements CalendarObserverInterface {

    private CalendarModel _model;
    private CalendarViewImpl _view;

    public CalendarPresenter(CalendarModel model, CalendarViewImpl view) {
        System.out.println("CalendarPresenter created");
        _model = model;
        _view = view;
        _view.addObserver(this);
    }

    @Override
    public void update(String actionName) {

    }
}

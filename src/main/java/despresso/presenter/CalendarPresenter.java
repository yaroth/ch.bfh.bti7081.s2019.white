package despresso.presenter;


import despresso.CalendarAction;
import despresso.logic.CalendarList;
import despresso.view.CalendarViewImpl;

public class CalendarPresenter implements ObserverInterface {

    private CalendarList model;
    private CalendarViewImpl view;

    public CalendarPresenter(CalendarList model, CalendarViewImpl view) {
        this.model = model;
        this.view = view;
        this.view.addObserver(this);
    }

    @Override
    public void update(String actionName) {
        if (actionName.equals(CalendarAction.CLICK_ME)) {
            // here the model is returning some value
            view.setLabel(model.getClickMeResult());
        } else if (actionName.equals(CalendarAction.RESET)) {
            view.setLabel(model.getResetResult());
        }
    }
}

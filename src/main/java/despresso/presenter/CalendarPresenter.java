package despresso.presenter;


import despresso.CalendarAction;
import despresso.logic.CalendarModel;
import despresso.view.CalendarViewImpl;
import org.vaadin.stefan.fullcalendar.FullCalendar;

public class CalendarPresenter implements ObserverInterface {

    private CalendarModel model;
    private CalendarViewImpl view;

    public CalendarPresenter(CalendarModel model, CalendarViewImpl view) {
        this.model = model;
        this.view = view;
        this.view.addObserver(this);
    }

    public FullCalendar getInitCalendar(){
        return model.getInitCalendar();
    }

    @Override
    public void update(String actionName) {
        /*
        if (actionName.equals(CalendarAction.CLICK_ME)) {
            // here the model is returning some value
            view.setLabel(model.getClickMeResult());
        } else if (actionName.equals(CalendarAction.RESET)) {
            view.setLabel(model.getResetResult());
        }
        */
    }
}

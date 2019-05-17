package despresso.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.CalendarAction;
import despresso.logic.CalendarModel;
import despresso.presenter.CalendarPresenter;
import despresso.presenter.ObserverInterface;
import org.vaadin.stefan.fullcalendar.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class CalendarViewImpl extends VerticalLayout implements SubjectInterface {


    private List<ObserverInterface> listeners = new ArrayList<>();
    private Label label;
    private CalendarPresenter _presenter;
    private FullCalendar _calendar;

    public CalendarViewImpl() {

        _calendar = new FullCalendar();
        _calendar.changeView(org.vaadin.stefan.fullcalendar.CalendarViewImpl.AGENDA_DAY);
        _calendar.setNowIndicatorShown(true);
        _calendar.setNumberClickable(true);
        _calendar.setTimeslotsSelectable(true);
        _calendar.setBusinessHours(
                new BusinessHours(LocalTime.of(9, 0), LocalTime.of(17, 0), BusinessHours.DEFAULT_BUSINESS_WEEK),
                new BusinessHours(LocalTime.of(12, 0), LocalTime.of(15, 0), DayOfWeek.SATURDAY)
        );

        // scheduler options
        //((Scheduler)_calendar).setSchedulerLicenseKey(Scheduler.GPL_V3_LICENSE_KEY);

        initBaseLayoutSettings();

        this.add(_calendar);
    }

    @Override
    public void removeObserver(ObserverInterface observer) {
        listeners.remove(observer);
    }

    @Override
    public void addObserver(ObserverInterface observer) {
        listeners.add(observer);
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

//    private Button createButton(String text) {
//        return new Button(text, event -> {
//            for (ObserverInterface listener : listeners) {
//                if (event.getSource().equals(clickMeBtn)) {
//                    listener.update(CalendarAction.CLICK_ME);
//                } else if (event.getSource().equals(resetBtn)){
//                    listener.update(CalendarAction.RESET);
//                }
//            }
//        });
//    }

    private void initBaseLayoutSettings() {
        setSizeFull();
        _calendar.setHeightByParent();
        setFlexStyles(true);
    }

    private void setFlexStyles(boolean flexStyles) {
        if (flexStyles) {
            _calendar.getElement().getStyle().set("flex-grow", "1");
            getElement().getStyle().set("display", "flex");
            getElement().getStyle().set("flex-direction", "column");
        } else {
            _calendar.getElement().getStyle().remove("flex-grow");
            getElement().getStyle().remove("display");
            getElement().getStyle().remove("flex-direction");
        }
    }
}

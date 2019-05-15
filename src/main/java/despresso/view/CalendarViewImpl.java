package despresso.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.CalendarAction;
import despresso.logic.CalendarModel;
import despresso.presenter.CalendarPresenter;
import despresso.presenter.ObserverInterface;
import org.vaadin.stefan.fullcalendar.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class CalendarViewImpl extends VerticalLayout implements SubjectInterface {


    private List<ObserverInterface> listeners = new ArrayList<>();
    private Button clickMeBtn, resetBtn;
    private Label label;
    private CalendarPresenter _presenter;

    public CalendarViewImpl() {

        FullCalendarScheduler calendar = new FullCalendarScheduler();
        calendar.changeView(SchedulerView.TIMELINE_DAY);
        calendar.setHeightAuto();
        calendar.setSchedulerLicenseKey("GPL-My-Project-Is-Open-Source");
                //FullCalendarBuilder.create().withScheduler().build();

        Resource resource = new Resource("asdf1", "testResource", "#fff");
        calendar.addResource(resource);

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(2);

        ResourceEntry resourceEntry = new ResourceEntry("asdf1.1", "testEntry", startTime, endTime, false, true, "#fff", "blablabla");
        resourceEntry.setResource(resource);

        //calendar.addEntry(new Entry());

        this.add(calendar);
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

    private Button createButton(String text) {
        return new Button(text, event -> {
            for (ObserverInterface listener : listeners) {
                if (event.getSource().equals(clickMeBtn)) {
                    listener.update(CalendarAction.CLICK_ME);
                } else if (event.getSource().equals(resetBtn)){
                    listener.update(CalendarAction.RESET);
                }
            }
        });
    }
}

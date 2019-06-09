package despresso.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.Views;
import despresso.logic.CalendarEntry;
import despresso.presenter.ObserverInterface;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@UIScope
@SpringComponent
public class HomeViewImpl extends VerticalLayout implements SubjectInterface<ObserverInterface> {

    private VerticalLayout mainMoodArea = new VerticalLayout();
    private VerticalLayout mainCalendarArea = new VerticalLayout();
    private List<ObserverInterface> listeners = new ArrayList<>();
    private MoodViewImpl moodView;
    private CalendarViewImpl calendarView;
    private CalendarEntry calendarEntry;
    private FullCalendar calendar = new FullCalendar();

    HorizontalLayout calendarTitleLine = new HorizontalLayout();

    private Button calendarConfirmButton;

    private Label label;

    public HomeViewImpl(MoodViewImpl moodView, CalendarViewImpl calendarView) {
        this.moodView = moodView;
        this.calendarView = calendarView;
        label = new Label();
        initView();
    }

    private void initView() {
        //showCalendarNotification();
        //adds MoodView
        mainMoodArea.add(moodView);

        //adds CalendarView
        Label calendarTitle = new Label("Your next appointment");
        calendarTitleLine.add(calendarTitle);
        this.mainCalendarArea.add(calendarTitleLine);
        calendarConfirmButton = createButton(Views.DONE);
        //mainCalendarArea.add(calendarView, calendarConfirmButton);
        loadNextDueCalendarEntry();
        mainCalendarArea.add(calendar);
        this.add(mainMoodArea, mainCalendarArea);

    }

    private void loadNextDueCalendarEntry() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Label label = new Label(date.format(formatter));
        List<CalendarEntry> nextCalendarEntries = calendarView.getNextCalendarEntriesSorted();
        if (!nextCalendarEntries.isEmpty()) {
            CalendarEntry nextEntry = nextCalendarEntries.get(0);
            calendar.addEntry(new Entry("", nextEntry.getTitle(), nextEntry.getStart(), nextEntry.getEnd(), false, true, nextEntry.getDescription(), nextEntry.getColor()));
            //calendarEntry = new CalendarEntry(nextEntry.getUserId(), nextEntry.getStart(), nextEntry.getEnd(), nextEntry.getTitle(), nextEntry.getDescription(), nextEntry.getColor(), nextEntry.getIsDone());
        } else {

        }



    }

    @Override
    public void removeObserver(ObserverInterface observer) {
        listeners.remove(observer);
    }

    @Override
    public void addObserver(ObserverInterface observer) {
        listeners.add(observer);

    }


    /*private void showCalendarNotification() {

        Label calendarEntry = new Label(
                "CalendarEntry");
        calendarConfirmButton = createButton(Views.DONE);

        line3.add(calendarEntry, calendarConfirmButton);
    }*/




    private Button createButton(Views view) {
        if (view.getIcon() != null){
            return new Button(view.getIcon(), event -> this.registerObject(event));
        } else {
            return new Button(view.toString(), event -> this.registerObject(event));
        }
    }

    private void registerObject(ClickEvent event) {
        for (ObserverInterface listener : listeners) {
            if (event.getSource().equals(calendarConfirmButton)) {
                listener.update(Views.DONE.toString());
            }
        }
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    public void loadMoodView() {
        mainMoodArea.removeAll();
        mainMoodArea.add(moodView);
    }

    // Reset the home view
    public void resetView() {
        remove(mainMoodArea, mainCalendarArea);
        mainMoodArea = new VerticalLayout();
        mainCalendarArea = new VerticalLayout();
        moodView.resetView();
        initView();
    }

    public void addConfirmationDialog(String text){

        Dialog dialog = new Dialog();
        dialog.add(new Label(text));

        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);
        Label messageLabel = new Label();

        Button confirmButton;
        confirmButton = new Button("Confirm", event -> {
            messageLabel.setText("Confirmed!");
            for (ObserverInterface listener : listeners)
                listener.update(Views.CONFIRM.toString());
            dialog.close();
        });

        confirmButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        Button cancelButton = new Button("Cancel", event -> {
            messageLabel.setText("Cancelled...");
            for (ObserverInterface listener : listeners)
                listener.update(Views.CANCEL.toString());
            dialog.close();
        });
        dialog.add(confirmButton, cancelButton);

        dialog.open();
    }

}

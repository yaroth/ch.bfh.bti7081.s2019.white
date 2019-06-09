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
import despresso.presenter.MainObserverInterface;
import despresso.presenter.ObserverInterface;
import org.vaadin.zhe.PaperRangeSlider;

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

    HorizontalLayout moodTitleLine = new HorizontalLayout();
    HorizontalLayout moodSliderContainer = new HorizontalLayout();
    HorizontalLayout calendarTitleLine = new HorizontalLayout();
    HorizontalLayout calendarContainer = new HorizontalLayout();

    private Button calendarConfirmButton;

    private Label label;

    public HomeViewImpl(MoodViewImpl moodView, CalendarViewImpl calendarView) {
        this.moodView = moodView;
        this.calendarView = calendarView;
        label = new Label();
        initView();
    }

    private void initView() {
        //initMoodViewContainer();
        //showCalendarNotification();
        this.mainMoodArea.add(moodTitleLine, moodSliderContainer);
        mainMoodArea.add(moodView);

        this.mainCalendarArea.add(calendarTitleLine, calendarContainer);
        this.mainCalendarArea.add(calendarView);
        this.add(mainMoodArea, mainCalendarArea);

    }

    @Override
    public void removeObserver(ObserverInterface observer) {
        listeners.remove(observer);
    }

    @Override
    public void addObserver(ObserverInterface observer) {
        listeners.add(observer);

    }


    private void initMoodViewContainer() {
        mainMoodArea.add(moodView);
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

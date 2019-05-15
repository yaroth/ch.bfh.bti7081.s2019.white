package despresso.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.Views;
import despresso.presenter.ObserverInterface;
import org.vaadin.zhe.PaperRangeSlider;

import java.util.ArrayList;
import java.util.List;

public class HomeViewImpl extends VerticalLayout implements SubjectInterface {


    private List<ObserverInterface> listeners = new ArrayList<>();
    HorizontalLayout line1 = new HorizontalLayout();
    HorizontalLayout line2 = new HorizontalLayout();
    HorizontalLayout line3 = new HorizontalLayout();
    private Button calendarConfirmButton;
    private Label label;

    public HomeViewImpl() {

        moodSliderHomeView();
        showCalendarNotification();

        this.add(line1);
        this.add(line2);
        this.add(line3);

    }

    private void showCalendarNotification() {

        Label calendarEntry = new Label(
                "CalendarEntry");
        calendarConfirmButton = createButton(Views.CONFIRM);

        line3.add(calendarEntry, calendarConfirmButton);
    }


    private void moodSliderHomeView() {

        Label label1 = new Label("How are you feeling today?");

        // Create a horizontal slider
        PaperRangeSlider paperRangeSlider = new PaperRangeSlider(-1, 1, 0, 0);
        paperRangeSlider.setStep(1);
        paperRangeSlider.setSingleSlider(true);
        Label currentMoodLabel = new Label("Your mood is " + paperRangeSlider.getValueMax());
        //Add listener
        paperRangeSlider.addMaxValueChangeListener(event -> currentMoodLabel.setText("Your mood is " + event.getValueMax()));
        paperRangeSlider.addMaxValueChangeListener(event -> {
            for (ObserverInterface listener : listeners)
                listener.update(Views.MOOD.toString());
        });


        line1.add(label1, currentMoodLabel);
        line2.add(new Label("Negative "), paperRangeSlider, new Label("Positive "));
    }

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
                listener.update(Views.SAVE.toString());
            }
        }
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }



    @Override
    public void removeObserver(ObserverInterface observer) {
        listeners.remove(observer);
    }

    @Override
    public void addObserver(ObserverInterface observer) {
        listeners.add(observer);

    }

}

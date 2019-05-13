package despresso.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.presenter.ObserverInterface;
import org.vaadin.zhe.PaperRangeSlider;

import java.util.ArrayList;
import java.util.List;

public class HomeViewImpl extends VerticalLayout implements SubjectInterface {


    private List<ObserverInterface> listeners = new ArrayList<>();
    HorizontalLayout line1 = new HorizontalLayout();
    HorizontalLayout line2 = new HorizontalLayout();
    HorizontalLayout line3 = new HorizontalLayout();

    public HomeViewImpl() {

        moodSliderHomeView();
        showCalendarNotification();

        this.add(line1);
        this.add(line2);
        this.add(line3);

    }

    private void showCalendarNotification() {
        Notification notification = Notification.show(
                "This is a notification created with static convenience method");
        line3.add(notification);
        /*Label content = new Label(
                "Hello, I am a notification with components!");
        NativeButton buttonInside = new NativeButton("Bye");
        Notification notification = new Notification(content, buttonInside);
        buttonInside.addClickListener(event -> notification.close());
        notification.setPosition(Notification.Position.MIDDLE);
        //buttonInside.addClickListener(event -> notification.open());
        line4.add(new Label("Notification: "), notification);*/
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
                listener.update(event.getValueMax());
        });


        line1.add(label1, currentMoodLabel);
        line2.add(new Label("Negative "), paperRangeSlider, new Label("Positive "));
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

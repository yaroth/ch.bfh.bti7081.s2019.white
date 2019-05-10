package despresso.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.presenter.ObserverInterface;
import org.vaadin.zhe.PaperRangeSlider;

import java.util.ArrayList;
import java.util.List;

public class HomeViewImpl extends VerticalLayout implements SubjectInterface {


    private List<ObserverInterface> listeners = new ArrayList<>();

    public HomeViewImpl() {
        HorizontalLayout line1 = new HorizontalLayout();
        Label label1 = new Label("How are you feeling today?");
        line1.add(label1);

        // Create a horizontal slider
        HorizontalLayout line2 = new HorizontalLayout();
        PaperRangeSlider paperRangeSlider = new PaperRangeSlider(1, 10, 1, 10);
        paperRangeSlider.setStep(1);
        paperRangeSlider.setSingleSlider(true);
        line2.add(new Label("Please enter a value from 1 to 10"), paperRangeSlider);

        HorizontalLayout line3 = new HorizontalLayout();
        Label label3 = new Label("HOME label 2");
        Label content = new Label(
                "Hello, I am a notification with components!");
        NativeButton buttonInside = new NativeButton("Bye");
        Notification notification = new Notification(content, buttonInside);
        notification.setDuration(3000);
        buttonInside.addClickListener(event -> notification.close());
        notification.setPosition(Notification.Position.MIDDLE);
        //buttonInside.addClickListener(event -> notification.open());
        line3.add(new Label("Notification: "), notification);
        this.add(line1);
        this.add(line2);
        this.add(line3);

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

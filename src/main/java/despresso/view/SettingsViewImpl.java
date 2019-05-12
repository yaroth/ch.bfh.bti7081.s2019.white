package despresso.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import despresso.presenter.ObserverInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsViewImpl extends VerticalLayout implements SubjectInterface {

    private Label label;
    private List<ObserverInterface> listeners = new ArrayList<>();

    public SettingsViewImpl() {
        HorizontalLayout line1 = new HorizontalLayout();
        label = new Label("Your personal settings for the despresso-app");
        line1.add(label);
        HorizontalLayout line2 = new HorizontalLayout();

        RadioButtonGroup<String> getNotifications = new RadioButtonGroup<>();
        getNotifications.setLabel("Do you want to get Notifications?");
        getNotifications.setItems("Yes", "No");
        line2.add(getNotifications);

        HorizontalLayout line3 = new HorizontalLayout();
        Button btn0 = createButton("Save Settings");
        Button btn1 = createButton("Delete Data");
        Button btn2 = createButton("Delete Account");

        line3.add(btn0, btn1, btn2);

        this.add(line1,line2,line3);

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
            for (ObserverInterface listener : listeners)
                listener.update(event);
        });
    }
}

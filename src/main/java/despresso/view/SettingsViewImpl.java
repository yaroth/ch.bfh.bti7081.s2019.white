package despresso.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.shared.Registration;
import despresso.presenter.ObserverInterface;
import com.vaadin.flow.component.dialog.Dialog;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsViewImpl extends VerticalLayout implements SubjectInterface {

    private Label label;
    private List<ObserverInterface> listeners = new ArrayList<>();
    private RadioButtonGroup<String> getNotifications = new RadioButtonGroup<>();
    private String getNotificationText = "Yes";

    public SettingsViewImpl() {
        HorizontalLayout line1 = new HorizontalLayout();
        label = new Label("Your personal settings for the despresso-app");
        line1.add(label);
        HorizontalLayout line2 = new HorizontalLayout();

        RadioButtonGroup<String> getNotifications = new RadioButtonGroup<>();
        getNotifications.setLabel("Do you want to get Notifications?");
        getNotifications.setItems("Yes", "No");
        getNotifications.addValueChangeListener(event -> getNotificationText = String.format(event.getValue()));

        line2.add(getNotifications);

        HorizontalLayout line3 = new HorizontalLayout();
        Button btn0 = createButton("Save Settings");

        Button btn1 = createButton("Delete Data");
        btn1.addThemeVariants(ButtonVariant.LUMO_ERROR);
        //add confirmation screen
        Button btn2 = createButton("Delete Account");
        btn2.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        //add confirmation screen


        line3.add(btn0, btn1, btn2);
        //add picker for UI settings.

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

    public String getRadiobuttonText() {
        return getNotificationText;
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
                listener.update(event);
            dialog.close();
        });

        confirmButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        Button cancelButton = new Button("Cancel", event -> {
            messageLabel.setText("Cancelled...");
            for (ObserverInterface listener : listeners)
                listener.update(event);
            dialog.close();
        });
        dialog.add(confirmButton, cancelButton);

        dialog.open();
    }
}

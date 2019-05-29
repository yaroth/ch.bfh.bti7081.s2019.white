package despresso.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.Views;
import despresso.presenter.ObserverInterface;
import com.vaadin.flow.component.dialog.Dialog;
import despresso.presenter.SettingsObserverInterface;

import java.util.ArrayList;
import java.util.List;

@UIScope
@SpringComponent
public class SettingsViewImpl extends VerticalLayout implements SubjectInterface<ObserverInterface> {
public class SettingsViewImpl extends VerticalLayout implements SubjectInterface<SettingsObserverInterface> {

    private Label label;
    private List<ObserverInterface> listeners = new ArrayList<>();
    private RadioButtonGroup<String> getNotifications = new RadioButtonGroup<>();
    private String getNotificationText = "Yes";
    private Button saveButton, deleteDataButton, deleteAccButton;

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

        saveButton = createButton(Views.SAVE);

        deleteDataButton = createButton(Views.DELETE_DATA);
        deleteDataButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

        deleteAccButton = createButton(Views.DELETE_ACCOUNT);
        deleteAccButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);


        line3.add(saveButton, deleteDataButton, deleteAccButton);
        //add picker for UI settings.

        this.add(line1,line2,line3);

    }

    @Override
    public void removeObserver(SettingsObserverInterface observer) {
        listeners.remove(observer);
    }

    @Override
    public void addObserver(SettingsObserverInterface observer) {
        listeners.add(observer);

    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    private Button createButton(Views view) {
        if (view.getIcon() != null){
            return new Button(view.getIcon(), event -> this.registerObject(event));
        } else {
            return new Button(view.toString(), event -> this.registerObject(event));
        }
    }

    private void registerObject(ClickEvent event) {
        for (SettingsObserverInterface listener : listeners) {
            if (event.getSource().equals(saveButton)) {
                listener.updateSaveButton();
            } else if (event.getSource().equals(deleteDataButton)) {
                listener.updateDeleteDataButton();
            } else if (event.getSource().equals(deleteAccButton)) {
                listener.updateDeleteAccountButton();
            }
        }
    }

    public String getRadiobuttonText () {
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
            for (SettingsObserverInterface listener : listeners)
                listener.updateConfirmButton();
            dialog.close();
        });

        confirmButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        Button cancelButton = new Button("Cancel", event -> {
            messageLabel.setText("Cancelled...");
            for (SettingsObserverInterface listener : listeners)
                listener.updateCancelButton();
            dialog.close();
        });
        dialog.add(confirmButton, cancelButton);

        dialog.open();
    }

    public void updateObservers() {
        for (ObserverInterface listener :listeners)
            listener.update(null);
    }
}

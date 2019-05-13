package despresso.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.SettingsAction;
import despresso.presenter.ObserverInterface;

import java.util.ArrayList;
import java.util.List;

public class SettingsViewImpl extends VerticalLayout implements SubjectInterface {

    private Label label;
    private Button clickMeBtn, resetBtn;
    private List<ObserverInterface> listeners = new ArrayList<>();

    public SettingsViewImpl() {
        HorizontalLayout line1 = new HorizontalLayout();
        label = new Label("Click the button below");
        line1.add(label);
        HorizontalLayout line2 = new HorizontalLayout();
        clickMeBtn = createButton(SettingsAction.CLICK_ME);
        resetBtn = createButton(SettingsAction.RESET);
        Button btn = new Button("test");
//        btn.addClickListener()
        line2.add(clickMeBtn, resetBtn);
        this.add(line1);
        this.add(line2);

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
                    listener.update(SettingsAction.CLICK_ME);
                } else if (event.getSource().equals(resetBtn)){
                    listener.update(SettingsAction.RESET);
                }
            }
        });
    }
}

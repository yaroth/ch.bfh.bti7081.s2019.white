package despresso.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.presenter.ObserverInterface;

import java.util.ArrayList;
import java.util.List;

public class SettingsViewImpl extends VerticalLayout implements SubjectInterface {

    private Label label;
    private List<ObserverInterface> listeners = new ArrayList<>();

    public SettingsViewImpl() {
        HorizontalLayout line1 = new HorizontalLayout();
        label = new Label("Click the button below");
        line1.add(label);
        HorizontalLayout line2 = new HorizontalLayout();
        Button btn1 = createButton("click me");
        Button btn2 = createButton("reset");
        Button btn = new Button("test");
//        btn.addClickListener()
        line2.add(btn1, btn2);
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
            for (ObserverInterface listener : listeners)
                listener.update(event);
        });
    }
}

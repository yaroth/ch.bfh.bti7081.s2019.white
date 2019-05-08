package despresso.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class SettingsViewImpl extends VerticalLayout implements SubjectInterface {

    private List<ObserverInterface> listeners = new ArrayList<>();

    public SettingsViewImpl() {
        HorizontalLayout line1 = new HorizontalLayout();
        Label label1 = new Label("SETTINGS some label 1");
        line1.add(label1);
        HorizontalLayout line2 = new HorizontalLayout();
        Label label2 = new Label("SETTINGS some label 2");
        line2.add(label2);
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

    public VerticalLayout getComponent() {
        return this;
    }
}

package despresso.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.presenter.ObserverInterface;

import java.util.ArrayList;
import java.util.List;

public class View extends VerticalLayout implements SubjectInterface {
    List<ObserverInterface> listeners = new ArrayList<>();

    Button createButton(String text) {
        return new Button(text, event -> {
            for (ObserverInterface listener : listeners)
                listener.update(event.getSource().getText());
        });
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

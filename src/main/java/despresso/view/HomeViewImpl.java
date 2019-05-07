package despresso.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeViewImpl extends VerticalLayout implements SubjectInterface {

    private List<ObserverInterface> listeners = new ArrayList<>();

    public HomeViewImpl() {

        // TODO: define main area dimensions
        HorizontalLayout mainArea = new HorizontalLayout();
        Label mainAreaLabel = new Label("Main Area Test Label");
        mainArea.add(mainAreaLabel);
        add(mainArea);

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

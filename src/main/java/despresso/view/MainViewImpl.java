package despresso.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class MainViewImpl extends VerticalLayout implements SubjectInterface {

    private static String MOOD = "Mood";
    private static String HOME = "Home";
    private static String TIPS = "Tips";
    private static String CALENDAR = "Calendar";
    private static String SETTINGS = "Settings";

    private Label label = new Label("despresso");

    private List<ObserverInterface> listeners = new ArrayList<>();

    public MainViewImpl() {

        // TODO: attach header to top
        // home - header - settings -> navigation
        HorizontalLayout header = new HorizontalLayout();
        header.add(createButton(HOME));
        header.add(label);
        header.add(createButton(SETTINGS));
        add(header);

        // TODO: define main area dimensions & load homeArea from HomeView
        // This is just to show that we have a main area which then will need to
        // filled with content.
        HorizontalLayout mainArea = new HorizontalLayout();
        mainArea.setHeight("200px");
        Label mainAreaLabel = new Label("Main Area Label for testing purposes only!");
        mainArea.add(mainAreaLabel);
        add(mainArea);

        // TODO: attach footer to bottom
        // navigation
        HorizontalLayout footer = new HorizontalLayout();
        footer.add(createButton(MOOD), createButton(CALENDAR), createButton(TIPS));
        add(footer);
    }

    private Button createButton(String text) {
        return new Button(text, event -> {
            for (ObserverInterface listener : listeners)
                listener.update(event);
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
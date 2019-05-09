package despresso.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.Views;
import despresso.presenter.ObserverInterface;

import java.util.ArrayList;
import java.util.List;


public class MainViewImpl extends VerticalLayout implements SubjectInterface {


    private final Label label = new Label("despresso");
    private HorizontalLayout mainArea = new HorizontalLayout();
    private HomeViewImpl homeView;
    private SettingsViewImpl settingsView;
    private MoodViewImpl moodView;
    private CalendarViewImpl calendarView;
    private TipsViewImpl tipsView;


    private List<ObserverInterface> listeners = new ArrayList<>();

    public MainViewImpl(HomeViewImpl homeView, SettingsViewImpl settingsView,
                        MoodViewImpl moodView, CalendarViewImpl calendarView, TipsViewImpl tipsView) {
        this.homeView = homeView;
        this.settingsView = settingsView;
        this.moodView = moodView;
        this.calendarView = calendarView;
        this.tipsView = tipsView;
        // TODO: attach header to top
        // home - header - settings -> navigation
        HorizontalLayout header = new HorizontalLayout();
        header.add(createButton(Views.HOME));
        header.add(label);
        header.add(createButton(Views.SETTINGS));
        add(header);

        // TODO: define main area dimensions & load homeArea from HomeView
        // This is just to show that we have a main area which then will need to
        // filled with content.
        mainArea.setHeight("200px");
        mainArea.add(this.homeView);
        add(mainArea);

        // TODO: attach footer to bottom
        // navigation
        HorizontalLayout footer = new HorizontalLayout();
        footer.add(createButton(Views.MOOD), createButton(Views.CALENDAR), createButton(Views.TIPS));
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


    public void loadSettingsView(ClickEvent event) {
        mainArea.removeAll();
        mainArea.add(settingsView);
    }

    public void loadHomeView(ClickEvent<Button> event) {
        mainArea.removeAll();
        mainArea.add(homeView);
    }

    public void loadMoodView(ClickEvent<Button> event) {
        mainArea.removeAll();
        mainArea.add(moodView);
    }

    public void loadCalendarView(ClickEvent<Button> event) {
        mainArea.removeAll();
        mainArea.add(calendarView);
    }

    public void loadTipsView(ClickEvent<Button> event) {
        mainArea.removeAll();
        mainArea.add(tipsView);
    }
}
package despresso.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.Views;
import despresso.presenter.ObserverInterface;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

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

    // Buttons
    private Button homeBtn, settingsBtn, moodBtn, calendarBtn, tipsBtn;

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
        homeBtn = createButton(Views.HOME);
        header.add(homeBtn);
        header.add(label);
        settingsBtn = createButton(Views.SETTINGS);
        header.add(settingsBtn);
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
        moodBtn = createButton(Views.MOOD);
        calendarBtn = createButton(Views.CALENDAR);
        tipsBtn = createButton(Views.TIPS);
        footer.add(moodBtn,calendarBtn,tipsBtn);
        add(footer);
    }

    private Button createButton(String text) {
        return new Button(text, event -> {
            for (ObserverInterface listener : listeners) {
                if (event.getSource().equals(homeBtn)) {
                    listener.update(Views.HOME);
                } else if (event.getSource().equals(settingsBtn)) {
                    listener.update(Views.SETTINGS);
                } else if (event.getSource().equals(moodBtn)) {
                    listener.update(Views.MOOD);
                } else if (event.getSource().equals(calendarBtn)) {
                    listener.update(Views.CALENDAR);
                } else if (event.getSource().equals(tipsBtn)) {
                    listener.update(Views.TIPS);
                }
            }
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


    public void loadSettingsView() {
        mainArea.removeAll();
        mainArea.add(settingsView);
    }

    public void loadHomeView() {
        mainArea.removeAll();
        mainArea.add(homeView);
    }

    public void loadMoodView() {
        mainArea.removeAll();
        mainArea.add(moodView);
    }

    public void loadCalendarView() {
        mainArea.removeAll();
        mainArea.add(calendarView);
    }

    public void loadTipsView() {
        mainArea.removeAll();
        mainArea.add(tipsView);
    }
}
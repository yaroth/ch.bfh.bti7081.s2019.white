package despresso.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.Views;
import despresso.presenter.MainObserverInterface;

import java.util.ArrayList;
import java.util.List;


public class MainViewImpl extends VerticalLayout implements MainSubjectInterface {


    private final Label label = new Label("despresso");
    private HorizontalLayout mainArea = new HorizontalLayout();
    private HomeViewImpl homeView;
    private SettingsViewImpl settingsView;
    private MoodViewImpl moodView;
    private CalendarViewImpl calendarView;
    private TipsViewImpl tipsView;

    // Buttons
    private Button homeBtn, settingsBtn, moodBtn, calendarBtn, tipsBtn;

    private List<MainObserverInterface> listeners = new ArrayList<>();

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
        homeBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        header.add(homeBtn);
        header.add(label);
        settingsBtn = createButton(Views.SETTINGS);
        settingsBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        header.add(settingsBtn);
        add(header);

        // TODO: define main area dimensions & load homeArea from HomeView
        // This is just to show that we have a main area which then will need to
        // filled with content.
        mainArea.setHeightFull();
        mainArea.setWidth("300px");
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

    private Button createButton(Views view) {
        if (view.getIcon() != null){
            return new Button(view.getIcon(), event -> this.registerObject(event));
        } else {
            return new Button(view.toString(), event -> this.registerObject(event));
        }
    }

    private void registerObject(ClickEvent event){
        for (MainObserverInterface listener : listeners) {
            if (event.getSource().equals(homeBtn)) {
                listener.loadHomeView();
            } else if (event.getSource().equals(settingsBtn)) {
                listener.loadSettingsView();
            } else if (event.getSource().equals(moodBtn)) {
                listener.loadMoodView();
            } else if (event.getSource().equals(calendarBtn)) {
                listener.loadCalendarView();
            } else if (event.getSource().equals(tipsBtn)) {
                listener.loadTipsView();
            }
        }
    }

    @Override
    public void removeObserver(MainObserverInterface observer) {
        listeners.remove(observer);
    }

    @Override
    public void addObserver(MainObserverInterface observer) {
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
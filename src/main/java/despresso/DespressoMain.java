package despresso;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import despresso.logic.*;
import despresso.presenter.*;
import despresso.view.*;

/**
 * The main view contains several buttons and  listeners.
 */
// TODO: to run despresso uncomment commented line and comment same line in CalculatorMain

@SpringComponent
@VaadinSessionScope
@Route("despresso.html")
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class DespressoMain extends VerticalLayout {

    public DespressoMain() {
        // Create the model and the Vaadin view implementation
        MainModel model = new MainModel();

        // Settings MVP
        SettingsViewImpl settingsView = new SettingsViewImpl();
        SettingsModel settingsModel = new SettingsModel();
        SettingsPresenter settingsPresenter = new SettingsPresenter(settingsModel, settingsView);

        // Mood MVP
        MoodViewImpl moodView = new MoodViewImpl();
        MoodModel moodModel = new MoodModel();
        MoodPresenter moodPresenter = new MoodPresenter(moodModel, moodView);

        // Calendar MVP
        CalendarViewImpl calendarView = new CalendarViewImpl();
        CalendarModel calendarModel = new CalendarModel();
        CalendarPresenter calendarPresenter = new CalendarPresenter(calendarModel, calendarView);

        // Home MVP
        HomeViewImpl homeView = new HomeViewImpl(moodView, calendarView);
        HomeModel homeModel = new HomeModel();
        HomePresenter homePresenter = new HomePresenter(homeModel, homeView);

        // Tips View MVP
        TipsViewImpl tipsView = new TipsViewImpl();
        TipModel tipModel = new TipModel();
//        TipPresenter tipPresenter = new TipPresenter(tipsView, tipModel);
        new TipPresenter(tipsView, tipModel);

        MainViewImpl view = new MainViewImpl(homeView, settingsView, moodView, calendarView, tipsView);
        // The presenter connects the model and view
        new MainPresenter(model, view);
        // The view implementation is a Vaadin component

        add(view);

    }
}

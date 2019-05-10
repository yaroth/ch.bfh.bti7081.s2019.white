package despresso;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import despresso.logic.MainModel;
import despresso.logic.SettingsModel;
import despresso.presenter.MainPresenter;
import despresso.presenter.SettingsPresenter;
import despresso.view.*;

/**
 * The main view contains several buttons and  listeners.
 */
// TODO: to run despresso uncomment commented line and comment same line in CalculatorMain
@Route("despresso.html")
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class DespressoMain extends VerticalLayout {
    /*public DespressoMain() {
        // Create the model and the Vaadin view implementation
        CalculatorModel model = new CalculatorModel();
        CalculatorViewImpl view = new CalculatorViewImpl();
        // The presenter connects the model and view
        new CalculatorPresenter(model, view);
        // The view implementation is a Vaadin component
        add(view);
    }*/

    public DespressoMain() {
        // Create the model and the Vaadin view implementation
        MainModel model = new MainModel();

        // Home MVP
        HomeViewImpl homeView = new HomeViewImpl();

        // Settings MVP
        SettingsViewImpl settingsView = new SettingsViewImpl();
        SettingsModel settingsModel = new SettingsModel();
        SettingsPresenter settingsPresenter = new SettingsPresenter(settingsModel, settingsView);

        MoodViewImpl moodView = new MoodViewImpl();

        CalendarViewImpl calendarView = new CalendarViewImpl();

        TipsViewImpl tipsView = new TipsViewImpl();

        MainViewImpl view = new MainViewImpl(homeView, settingsView, moodView, calendarView, tipsView);
        // The presenter connects the model and view
        new MainPresenter(model, view);
        // The view implementation is a Vaadin component
        add(view);

        System.out.println("Test out yann");
    }
}

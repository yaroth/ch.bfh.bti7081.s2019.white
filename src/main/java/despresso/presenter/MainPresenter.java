package despresso.presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import despresso.Views;
import despresso.logic.MainModel;
import despresso.view.MainViewImpl;

public class MainPresenter implements ObserverInterface {

    private MainModel model;
    private MainViewImpl view;

    public MainPresenter(MainModel model, MainViewImpl view) {
        this.model = model;
        this.view = view;
        this.view.addObserver(this);
    }

    // generic implementation so that presenter COULD do sth
    // when loading a new view into the main area.
    @Override
    public void update(String viewName) {
        model.doSomething();
        if (viewName.equals(Views.HOME)) {
            view.loadHomeView();
        } else if (viewName.equals(Views.SETTINGS)) {
            view.loadSettingsView();
        } else if (viewName.equals(Views.MOOD)) {
            view.loadMoodView();
        } else if (viewName.equals(Views.CALENDAR)) {
            view.loadCalendarView();
        } else if (viewName.equals(Views.TIPS )) {
            view.loadTipsView();
        }
    }
}

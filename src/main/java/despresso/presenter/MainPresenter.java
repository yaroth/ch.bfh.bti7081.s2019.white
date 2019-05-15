package despresso.presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValue;
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
    public void update(String button) {
        model.doSomething();
        if (button.equals(Views.HOME.toString())) {
            view.loadHomeView();
        } else if (button.equals(Views.SETTINGS.toString())) {
            view.loadSettingsView();
        } else if (button.equals(Views.MOOD.toString())) {
            view.loadMoodView();
        } else if (button.equals(Views.CALENDAR.toString())) {
            view.loadCalendarView();
        } else if (button.equals(Views.TIPS.toString())) {
            view.loadTipsView();
        }
    }


}

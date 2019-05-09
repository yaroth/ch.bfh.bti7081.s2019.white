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
    public void update(ClickEvent<Button> event, String btnLabel) {
        System.out.println("MainPresenter.update() method executed!");
        System.out.println("Button text : " + event.getSource().getText());
        model.doSomething();
        if (Views.HOME.equals(btnLabel)) {
            view.loadHomeView(event);
        } else if (Views.SETTINGS.equals(btnLabel)){
            view.loadSettingsView(event);
        }else if (Views.MOOD.equals(btnLabel)){
            view.loadMoodView(event);
        }else if (Views.CALENDAR.equals(btnLabel)){
            view.loadCalendarView(event);
        }else if (Views.TIPS.equals(btnLabel)){
            view.loadTipsView(event);
        }
    }
}

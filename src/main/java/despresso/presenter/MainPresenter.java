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
    public void update(ClickEvent<Button> event) {
        model.doSomething();
        if (Views.HOME.equals(event.getSource().getText())) {
            view.loadHomeView(event);
        } else if (Views.SETTINGS.equals(event.getSource().getText())){
            view.loadSettingsView(event);
        }else if (Views.MOOD.equals(event.getSource().getText())){
            view.loadMoodView(event);
        }else if (Views.CALENDAR.equals(event.getSource().getText())){
            view.loadCalendarView(event);
        }else if (Views.TIPS.equals(event.getSource().getText())){
            view.loadTipsView(event);
        }
    }

    @Override
    public void updateFromChangeEvent(HasValue.ValueChangeEvent event) {
        
    }
}

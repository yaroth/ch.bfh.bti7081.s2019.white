package despresso.presenter;

import despresso.Views;
import despresso.logic.MainModel;
import despresso.view.MainViewImpl;

public class MainPresenter implements MainObserverInterface {

    private MainModel model;
    private MainViewImpl view;

    public MainPresenter(MainModel model, MainViewImpl view) {
        this.model = model;
        this.view = view;
        this.view.addObserver(this);
    }

    @Override
    public void homeBtnClicked() {
        model.doSomething();
        view.loadHomeView();
    }

    @Override
    public void settingsBtnClicked() {
        model.doSomething();
        view.loadSettingsView();
    }

    @Override
    public void moodBtnClicked() {
        model.doSomething();
        view.loadMoodView();
    }

    @Override
    public void calendarBtnClicked() {
        model.doSomething();
        view.loadCalendarView();
    }

    @Override
    public void tipsBtnClickec() {
        model.doSomething();
        view.loadTipsView();
    }


    @Override
    public void update(String name) {

    }
}

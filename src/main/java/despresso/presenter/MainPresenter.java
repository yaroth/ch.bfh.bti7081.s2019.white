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
    public void loadHomeView() {
        model.doSomething();
        view.loadHomeView();
    }

    @Override
    public void loadSettingsView() {
        model.doSomething();
        view.loadSettingsView();
    }

    @Override
    public void loadMoodView() {
        model.doSomething();
        view.loadMoodView();
    }

    @Override
    public void loadCalendarView() {
        model.doSomething();
        view.loadCalendarView();
    }

    @Override
    public void loadTipsView() {
        model.doSomething();
        view.loadTipsView();
    }


}

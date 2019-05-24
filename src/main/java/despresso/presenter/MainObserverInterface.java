package despresso.presenter;

public interface MainObserverInterface extends ObserverInterface{
    void loadHomeView();
    void loadSettingsView();
    void loadMoodView();
    void loadCalendarView();
    void loadTipsView();
}
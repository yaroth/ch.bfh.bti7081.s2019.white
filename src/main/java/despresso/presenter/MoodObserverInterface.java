package despresso.presenter;

public interface MoodObserverInterface extends ObserverInterface {

    //Buttons
    void moodSelectionButton();
    void saveButton();
    void undoButton();
    void specifyButton();
    void confirmAccuracyButton();

    //Slider
    void moodSlider();

    void mainMoodSlider();
}

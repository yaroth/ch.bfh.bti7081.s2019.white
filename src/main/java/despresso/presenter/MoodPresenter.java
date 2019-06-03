package despresso.presenter;

import despresso.logic.MoodModel;
import despresso.view.MoodViewImpl;

import java.util.ArrayList;

public class MoodPresenter implements MoodObserverInterface {
    private MoodModel model;
    private MoodViewImpl view;

    public MoodPresenter(MoodModel model, MoodViewImpl view) {
        this.model = model;
        this.model.setPresenter(this);
        this.view = view;
        this.view.addObserver(this);
    }

    @Override
    public void moodSelectionButton() {
        setMood();
    }

    @Override
    public void saveButton() {
        resetMoodView();
    }

    @Override
    public void undoButton() {
        undoMoodSelection();
    }

    @Override
    public void specifyButton() {
        showSpecificMoods();
    }

    @Override
    public void confirmAccuracyButton() {
        resetMoodView();
    }

    // Model: Set the accuracy of the mood selected with the slider
    @Override
    public void moodSlider() {
        model.setMoodAccuracy(view.getMoodSliderValue());
    }

    // Model: Set selected mood
    private void setMood() {
        model.setMood(view.getSelectedMood());
    }

    // View: Display options for selected mood
    public void moodUpdated(String newMood) {
        view.showMoodOptions(newMood);
    }

    // View: Show more specific moods for the selected one
    private void showSpecificMoods() {
        ArrayList<String> specificMoods = model.specifyMood();
        System.out.println("Showing specific moods: " + model.specifyMood());
        if(specificMoods == null) {
            view.showMoodSlider();
        } else
            view.showSpecificMoods(specificMoods);
    }

    //View: Undo mood selection
    private void undoMoodSelection() {
        model.undoMoodSelection();
        view.undoMoodSelection();
    }

    //Reset the view and current mood
    private void resetMoodView() {
        this.model.resetMood();
        this.view.resetView();
    }

    @Override
    public void update(String name) {
        System.out.println("MoodView: Something called generic update in ObservableInterface with string: " + name);
    }
}

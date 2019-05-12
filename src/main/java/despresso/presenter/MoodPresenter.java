package despresso.presenter;

import despresso.logic.MoodModel;
import despresso.view.MoodViewImpl;

import java.util.ArrayList;

public class MoodPresenter implements ObserverInterface {
    private MoodModel model;
    private MoodViewImpl view;

    public MoodPresenter(MoodModel model, MoodViewImpl view) {
        this.model = model;
        this.model.setPresenter(this);
        this.view = view;
        this.view.addObserver(this);
    }

    @Override
    public void update(String someString) {
        switch(someString) {
            case "Mood":
                setMood();
                break;
            case "Save":
                resetMoodView();
                break;
            case "Undo":
                undoMoodSelection();
                break;
            case "Specify":
                showSpecificMoods();
                break;
            case "Confirm":
                resetMoodView();
                break;
            default:
                System.out.println("Unknown command!");
        }
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

}

package despresso.logic;

import despresso.MoodState;
import despresso.presenter.MoodPresenter;

import java.util.ArrayList;

public class MoodModel {
    private MoodPresenter presenter;
    private MoodState moodState = new MoodState();

    public void setPresenter(MoodPresenter presenter) {
        this.presenter = presenter;
    }

    public void setMood(String newMood) {
        this.moodState.setCurrentMood(newMood);
        this.presenter.moodUpdated(newMood);
    }

    public ArrayList<String> specifyMood() {
        return moodState.splitMood();
    }

    public void resetMood() {
        this.moodState.setCurrentMood("None");
    }

    public void undoMoodSelection() {
        this.moodState.undoMoodSelection();
    }
}

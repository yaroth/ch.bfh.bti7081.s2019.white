package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.MoodState;
import despresso.presenter.MoodPresenter;

import java.util.ArrayList;

@UIScope
@SpringComponent
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

    public void setMoodAccuracy(int moodSliderValue) {
        this.moodState.setMoodAccuracy(moodSliderValue);
    }
}

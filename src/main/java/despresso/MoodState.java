package despresso;

import java.util.ArrayList;
import java.util.Arrays;

public class MoodState {

    private String currentMood = "None";
    private String previousMood = "None";
    private int moodAccuracy = -1;

    public MoodState () {

    }

    public String getCurrentMood() {
        return currentMood;
    }

    public void setCurrentMood(String currentMood) {
        this.previousMood = this.currentMood;
        this.currentMood = currentMood;
    }

    public ArrayList<String> splitMood() {
        switch(currentMood) {
            case "Good":
                return new ArrayList<>( Arrays.asList("Good1", "Good2", "Good3", "Good4"));
            case "Bad":
                return new ArrayList<>( Arrays.asList("Bad1", "Bad2", "Bad3", "Bad4"));
            default:
                return null;
        }
    }

    public void undoMoodSelection() {
        setCurrentMood(previousMood);
    }

    public void setMoodAccuracy(int moodAccuracy) {
        this.moodAccuracy = moodAccuracy;
        System.out.println("Accuracy set to: " + moodAccuracy);
    }
}
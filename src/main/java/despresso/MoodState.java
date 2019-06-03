package despresso;

import despresso.logic.Mood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MoodState {

    private Mood currentMood = null;
    private Mood previousMood = null;

    private HashMap<String, Mood> moods = new HashMap<>();

    private int moodAccuracy = -1;

    public MoodState () {
        // Base moods here
        this.moods.put("Good", new Mood("Good", new String[]{"Awesome", "Dandy", "Spiffing", "Splendid"}));
        this.moods.put("Bad", new Mood("Bad",  new String[]{"Gruesome", "Awful", "Devastated", "Crappy"}));
        // Fill up specific moods
        for(String submood : this.moods.get("Good").getSubMoods())
            this.moods.put(submood, new Mood(submood));
        for(String submood : this.moods.get("Bad").getSubMoods())
            this.moods.put(submood, new Mood(submood));
    }

    public String getCurrentMood() {
        return currentMood == null ?
                "None" : currentMood.getName();
    }

    public void setCurrentMood(String currentMood) {
        this.previousMood = this.currentMood;
        this.currentMood = moods.get(currentMood);
        System.out.println("Current mood set to: " + this.currentMood);
    }

    public ArrayList<String> splitMood() {
        return currentMood.getSubMoods();
    }

    public void undoMoodSelection() {
        setCurrentMood(previousMood.getName());
    }

    public void setMoodAccuracy(int moodAccuracy) {
        this.moodAccuracy = moodAccuracy;
        System.out.println("Accuracy set to: " + moodAccuracy);
    }
}
package despresso.presenter;

import despresso.logic.Tip;

import java.util.ArrayList;

public interface TipObserverInterface {
    // Update functions for feeling check boxes:
    void updateFeelingAnger();
    void updateFeelingDisgust();
    void updateFeelingAnxiety();
    void updateFeelingSadness();
    void updateFeelingFear();

    // Update functions for radio buttons
    void updateLocation();
    void updateType();
    void updateDuration();
    // Update functions for confirm/cancel buttons
    void updateOk();
    // Update Tiplist
    void updateTiplist(ArrayList<Tip> tipList);

}
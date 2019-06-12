import despresso.DataType;
import despresso.logic.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FilterTipListTest {
    private TipModel tipModel = new TipModel();


    @Test
    public void testFilterTipList() {
        boolean anger = false;
        boolean disgust = false;
        boolean anxiety = false;
        boolean sadness = false;
        boolean fear = true;

        TipLocation tipLocation = TipLocation.ATHOME;
        TipType tipType = TipType.BODY;
        TipDuration tipDuration = TipDuration.SHORT;

        Tip filterTip = new Tip(anger, disgust, anxiety, sadness, fear, tipDuration, tipType, tipLocation, "");

        this.tipModel.filterTipList(filterTip);

        boolean angerResult = true;
        boolean disgustResult = true;
        boolean anxietyResult = true;
        boolean sadnessResult = true;
        boolean fearResult = true;
        boolean locationResult = true;
        boolean typeResult = true;
        boolean durationResult = true;

        for(int i = 0; i < tipModel.getFilteredTipList().size(); i++){
            System.out.println("anger: " + angerResult);
            System.out.println("disgust: " + disgustResult);
            System.out.println("anxiety: " + anxietyResult);
            System.out.println("sadness: " + sadnessResult + " // sadness of tip: " + this.tipModel.getFilteredTipList().get(i).getSadness());
            System.out.println("fear: " + fearResult);
            System.out.println("location: " + locationResult);
            System.out.println("type: " + typeResult);
            System.out.println("duration: " + durationResult);
            angerResult = angerResult & (this.tipModel.getFilteredTipList().get(i).getAnger() == anger);
            disgustResult = disgustResult & (this.tipModel.getFilteredTipList().get(i).getDisgust() == disgust);
            anxietyResult = anxietyResult & (this.tipModel.getFilteredTipList().get(i).getAnxiety() == anxiety);
            sadnessResult = sadnessResult & (this.tipModel.getFilteredTipList().get(i).getSadness() == sadness);
            fearResult = fearResult & (this.tipModel.getFilteredTipList().get(i).getFear() == fear);
            locationResult = locationResult & (this.tipModel.getFilteredTipList().get(i).getTipLocation() == tipLocation);
            typeResult = typeResult & (this.tipModel.getFilteredTipList().get(i).getTipType() == tipType);
            durationResult = durationResult & (this.tipModel.getFilteredTipList().get(i).getTipDuration() == tipDuration);
        }
        assertTrue(angerResult);
        assertTrue(disgustResult);
        assertTrue(anxietyResult);
        assertFalse(sadnessResult);
        assertTrue(fearResult);
        assertTrue(locationResult);
        assertTrue(typeResult);
        assertTrue(durationResult);
    }
}


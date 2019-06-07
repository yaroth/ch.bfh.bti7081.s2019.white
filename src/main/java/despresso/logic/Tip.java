package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class Tip {
    // tip feelings
    private boolean anger;
    private boolean disgust;
    private boolean anxiety;
    private boolean sadness;
    private boolean fear;

    private int id;
    private TipDuration tipDuration;
    private TipType tipType;
    private TipLocation tipLocation;
    private String description;

    public Tip(){}
    public Tip(boolean anger, boolean disgust, boolean anxiety, boolean sadness, boolean fear, TipDuration tipDuration, TipType tipType, TipLocation tipLocation, String description){
        this.anger = anger;
        this.disgust = disgust;
        this.anxiety = anxiety;
        this.sadness = sadness;
        this.fear = fear;
        this.tipDuration = tipDuration;
        this.tipLocation = tipLocation;
        this.tipType = tipType;
        this.description = description;
    }

    public Tip(TipDuration tipDuration, TipType tipType, TipLocation tipLocation, String description){
        this.anger = false;
        this.disgust = false;
        this.anxiety = false;
        this.sadness = false;
        this.fear = false;
        this.tipDuration = tipDuration;
        this.tipLocation = tipLocation;
        this.tipType = tipType;
        this.description = description;
    }

    public TipDuration getTipDuration() {
        return tipDuration;
    }

    public void setTipDuration(TipDuration tipDuration) {
        this.tipDuration = tipDuration;
    }

    public TipType getTipType() {
        return tipType;
    }

    public void setTipType(TipType tipType) {
        this.tipType = tipType;
    }

    public TipLocation getTipLocation() {
        return tipLocation;
    }

    public void setTipLocation(TipLocation tipLocation) {
        this.tipLocation = tipLocation;
    }

    public String getDescription() {
        return description;
    }

    public boolean getAnger() {
        return anger;
    }

    public void setAnger(boolean anger) {
        this.anger = anger;
    }

    public boolean getDisgust() {
        return disgust;
    }

    public void setDisgust(boolean disgust) {
        this.disgust = disgust;
    }

    public boolean getAnxiety() {
        return anxiety;
    }

    public void setAnxiety(boolean anxiety) {
        this.anxiety = anxiety;
    }

    public boolean getSadness() {
        return sadness;
    }

    public void setSadness(boolean sadness) {
        this.sadness = sadness;
    }

    public boolean getFear() {
        return fear;
    }

    public void setFear(boolean fear) {
        this.fear = fear;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){

        String tiptoDB = String.format("insert into tip (location, type, duration, description) values ( %d, %d, %d, '%s');", tipLocation.ordinal(), tipType.ordinal(), tipDuration.ordinal(), description);

        return tiptoDB;
    }
}

package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class Tip {
    private TipDuration tipDuration;
    private TipType tipType;
    private TipLocation tipLocation;
    private String description;

    public Tip(TipDuration tipDuration, TipType tipType, TipLocation tipLocation, String description){
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

}

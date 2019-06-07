package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UIScope
@SpringComponent
public class TipModel {
    private ArrayList<Tip> tipList = new ArrayList<>();
    private ArrayList<Tip> filteredTiplist = new ArrayList<>();

    public  TipModel(){
        for(int i = 0; i <= 30; i++){
            this.tipList.add(new Tip(this.convertToBoolean(i), this.convertToBoolean(i), this.convertToBoolean(i), this.convertToBoolean(i+1), this.convertToBoolean(i+1),
                    TipDuration.values()[i%3], TipType.values()[i%2], TipLocation.values()[i%3], "description"+i));
        }
        for (Tip t : tipList) {
            System.out.println(t);
        }
    }

    /** filterTipList() takes as input duration or type or location of tip filters the tipList accordingly. */
    public void filterTipList(Tip tip) {
        this.filteredTiplist = (ArrayList<Tip>) this.tipList.stream()
                .filter(e -> e.getAnger() == tip.getAnger() || !tip.getAnger())
                .filter(e -> e.getDisgust() == tip.getDisgust() || !tip.getDisgust())
                .filter(e -> e.getAnxiety() == tip.getAnxiety() || !tip.getAnxiety())
                .filter(e -> e.getSadness() == tip.getSadness() || !tip.getSadness())
                .filter(e -> e.getFear() == tip.getFear() || !tip.getFear())
                .filter(e -> e.getTipDuration() == tip.getTipDuration() || tip.getTipDuration() == null)
                .filter(e -> e.getTipLocation() == tip.getTipLocation() || tip.getTipLocation() == null)
                .filter(e -> e.getTipType() == tip.getTipType() || tip.getTipType() == null)
                .collect(Collectors.toList());
    }


    public ArrayList<Tip> getTipList() {
        return this.tipList;
    }
    public ArrayList<Tip> getFilteredTipList(){return this.filteredTiplist;}
    private Boolean convertToBoolean(int i) {
        return (i%2) == 1;
    }
}

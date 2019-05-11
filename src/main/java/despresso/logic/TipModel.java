package despresso.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TipModel {
    private ArrayList<Tip> tipList;
    private List<Tip> filteredTiplist;

    public  TipModel(){
        for(int i = 0; i <= 10; i++){
            tipList.add(new Tip(TipDuration.values()[i%3], TipType.values()[i%2], TipLocation.values()[i%3], "description"+i));
        }
    }

    /** filterTipList() takes as input duration or type or location of tip filters the tipList accordingly. */
    public void filterTipList(TipDuration duration, TipType type, TipLocation location){
        System.out.println("TipList.filterTipList() executed...");
        this.filteredTiplist = this.tipList.stream()
                .filter(e -> e.getTipDuration() == duration || e.getTipType() == type || e.getTipLocation() == location)
                .collect(Collectors.toList());
    }


    public ArrayList<Tip> getTipList() {
        return this.tipList;
    }
}

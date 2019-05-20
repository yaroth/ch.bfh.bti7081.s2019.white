package despresso.presenter;


import despresso.logic.Tip;
import despresso.logic.TipModel;
import despresso.view.TipsViewImpl;

import java.util.ArrayList;

public class TipPresenter implements TipObserverInterface {

    private TipsViewImpl tipView;
    private TipModel tipModel;

    public TipPresenter(TipsViewImpl view, TipModel tipModel) {
        System.out.println("TipPresenter created");
        this.tipModel = tipModel;
        this.tipView = view;
        this.tipView.setTipList(this.tipModel.getTipList());
        this.tipView.addObserver(this);
        this.updateTiplist(this.tipModel.getTipList());
    }


    @Override
    public void updateFeelingAnger() {
        System.out.println("TipPresenter.updateFeelingAnger clicked");
    }

    @Override
    public void updateFeelingDisgust() {
        System.out.println("TipPresenter.updateFeelingDisgust clicked");

    }

    @Override
    public void updateFeelingAnxiety() {
        System.out.println("TipPresenter.updateFeelingAnxiety clicked");

    }

    @Override
    public void updateFeelingSadness() {
        System.out.println("TipPresenter.updateFeelingSadness clicked");

    }

    @Override
    public void updateFeelingFear() {
        System.out.println("TipPresenter.updateFeelingFear clicked");

    }

    @Override
    public void updateLocation() {
        System.out.println("TipPresenter.updateLocation clicked");

    }

    @Override
    public void updateType() {
        System.out.println("TipPresenter.updateType clicked");

    }

    @Override
    public void updateDuration() {
        System.out.println("TipPresenter.updateDuration clicked");

    }

    @Override
    public void updateOk() {
        System.out.println("TipPresenter.updateOk clicked");
        this.updateTiplist(this.tipModel.getTipList());
    }

    @Override
    public void updateCancel() {
        System.out.println("TipPresenter.updateCancel clicked");
    }

    @Override
    public void updateTiplist(ArrayList<Tip> tipList) {
        System.out.println("TipPresenter.updateTiplist clicked");
        this.tipView.setTipList(tipList);
        System.out.println(tipList);
    }


}



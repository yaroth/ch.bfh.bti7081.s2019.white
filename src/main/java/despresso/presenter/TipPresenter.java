package despresso.presenter;


import despresso.logic.*;
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
        this.tipView.setTipList(this.tipModel.getTipList());
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
        // make new tip with filters from view. This tip is used from the model to filter the tiplist.
        boolean anger = this.tipView.getAnger();
        boolean disgust = this.tipView.getDisgust();
        boolean anxiety = this.tipView.getAnxiety();
        boolean sadness = this.tipView.getSadness();
        boolean fear = this.tipView.getFear();

        TipLocation tipLocation = this.tipView.getTipLocation();
        TipType tipType = this.tipView.getTipType();
        TipDuration tipDuration = this.tipView.getDuration();

        Tip filterTip = new Tip(anger, disgust, anxiety, sadness, fear, tipDuration, tipType, tipLocation, "");
        // filter tiplist
        this.tipModel.filterTipList(filterTip);
        // display filtered Tiplist
        this.tipView.setTipList(this.tipModel.getFilteredTipList());

        System.out.println("TipPresenter.updateOk clicked");
    }

    @Override
    public void updateCancel() {
        // clear Buttons
        this.tipView.clearAnger();
        this.tipView.clearAnxiety();
        this.tipView.clearDisgust();
        this.tipView.clearFear();
        this.tipView.clearSadness();
        this.tipView.clearLocation();
        this.tipView.clearType();
        this.tipView.clearDuration();
        // display unfiltered tiplist
        this.tipView.setTipList(this.tipModel.getTipList());
        System.out.println("TipPresenter.updateCancel clicked");
    }

    @Override
    public void update(String viewName) {

    }
}



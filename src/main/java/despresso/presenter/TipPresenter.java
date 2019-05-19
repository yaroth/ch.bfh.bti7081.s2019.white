package despresso.presenter;


import despresso.logic.TipModel;
import despresso.view.TipsViewImpl;

public class TipPresenter implements TipObserverInterface {

    private TipsViewImpl tipView;
    private TipModel tipModel;

    public TipPresenter(TipsViewImpl view, TipModel tipModel) {
        System.out.println("TipPresenter created");
        this.tipModel = tipModel;
        this.tipView = view;
        this.tipView.setTipList(this.tipModel.getTipList());
        this.tipView.addObserver(this);
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

    }


}



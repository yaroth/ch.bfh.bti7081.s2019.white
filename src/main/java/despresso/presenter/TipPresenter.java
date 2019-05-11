package despresso.presenter;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import despresso.logic.TipModel;
import despresso.view.TipsViewImpl;

public class TipPresenter implements ObserverInterface{

    private TipsViewImpl tipView;
    private TipModel tipModel;

    public TipPresenter(TipsViewImpl view, TipModel tipModel) {
        this.tipModel = tipModel;
        this.tipView = view;
        this.tipView.addObserver(this);
    }

//    public void updateRadioGroupButtons(AbstractField.ComponentValueChangeEvent<RadioButtonGroup<String>, String> event){
//
//    }

    @Override
    public void update(ClickEvent<Button> event) {
        this.tipView.testLabel.setText("Some Button Clicked");

    }
}



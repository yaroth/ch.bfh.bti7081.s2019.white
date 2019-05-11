package despresso.presenter;


import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import despresso.logic.TipModel;
import despresso.view.TipsViewImpl;

public class TipPresenter implements ObserverInterface{

    private TipsViewImpl view;
    private TipModel tipModel;

    public TipPresenter(TipsViewImpl view, TipModel tipModel) {
        this.tipModel = tipModel;
        this.view = view;
        this.view.addObserver(this);
    }

    @Override
    public void update(ClickEvent<Button> event) {

    }
}



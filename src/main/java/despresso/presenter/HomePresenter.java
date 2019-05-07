package despresso.presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import despresso.logic.MainModel;
import despresso.view.HomeViewImpl;
import despresso.view.ObserverInterface;

public class HomePresenter  {

    private MainPresenter mainPresenter;
    private HomeViewImpl view;

    public HomePresenter(MainPresenter mainPresenter, HomeViewImpl view) {
        this.mainPresenter = mainPresenter;
        this.view = view;
    }


}

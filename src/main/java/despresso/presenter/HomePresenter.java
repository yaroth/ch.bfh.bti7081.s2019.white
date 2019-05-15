package despresso.presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import despresso.logic.HomeModel;
import despresso.view.HomeViewImpl;

public class HomePresenter implements ObserverInterface {

    private HomeModel homeModel;
    private HomeViewImpl view;

    public HomePresenter(HomeModel homeModel, HomeViewImpl view) {
        this.homeModel = homeModel;
        this.view = view;
        this.view.addObserver(this);
    }


    @Override
    public void update(String someString) {

    }

    @Override
    public void updateFromChangeEvent(HasValue.ValueChangeEvent event) {

    }
}

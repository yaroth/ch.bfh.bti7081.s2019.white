package despresso.presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import despresso.Views;
import despresso.logic.HomeModel;
import despresso.view.HomeViewImpl;
import despresso.view.MainViewImpl;

public class HomePresenter implements ObserverInterface {

    private HomeModel homeModel;
    private HomeViewImpl view;
    private MainViewImpl mainView;
    private String confirmed = "";

    public HomePresenter(HomeModel homeModel, HomeViewImpl view) {
        this.homeModel = homeModel;
        this.view = view;
        this.view.addObserver(this);
    }


    @Override
    public void update(String input) {
        if (input.equals(Views.MOOD.toString())) {
            mainView.loadMoodView();
        } else if (input.equals(Views.CONFIRM.toString())){
            if (confirmed =="ok"){
                view.setLabel(homeModel.closeCalendarEntry());
            } else {
                view.setLabel("not confirmed");
            }
        } else {
            view.setLabel("not confirmed");
            confirmed = "";
        }
    }


}

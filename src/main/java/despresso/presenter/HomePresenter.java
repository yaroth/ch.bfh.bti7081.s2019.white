package despresso.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.logic.HomeModel;
import despresso.view.HomeViewImpl;

@UIScope
@SpringComponent
public class HomePresenter implements ObserverInterface {

    private HomeModel homeModel;
    private HomeViewImpl view;
    private String confirmed = "";

    public HomePresenter(HomeModel homeModel, HomeViewImpl view) {
        this.homeModel = homeModel;
        this.view = view;
        this.view.addObserver(this);
    }


    @Override
    public void update(String input) {
        switch(input) {
            case "MoodSliderUpdate":
                view.loadMoodView();
                break;
            case "Done":
                view.closeCalendarEntry();
                break;
            default:
                System.out.println("Unknown command!");
        }
    }
}

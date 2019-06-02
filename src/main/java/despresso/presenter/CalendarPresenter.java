package despresso.presenter;


import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.logic.CalendarModel;
import despresso.view.CalendarViewImpl;

@UIScope
@SpringComponent
public class CalendarPresenter implements ObserverInterface {

    private CalendarModel model;
    private CalendarViewImpl view;

    public CalendarPresenter(CalendarModel model, CalendarViewImpl view) {
        this.model = model;
        this.view = view;
        this.view.addObserver(this);
    }

    @Override
    public void update(String actionName) {

    }
}

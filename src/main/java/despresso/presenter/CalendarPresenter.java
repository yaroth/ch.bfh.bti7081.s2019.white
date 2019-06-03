package despresso.presenter;


import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.logic.CalendarModel;
import despresso.view.CalendarViewImpl;


@UIScope
@SpringComponent
public class CalendarPresenter implements CalendarObserverInterface {

    private CalendarModel _model;
    private CalendarViewImpl _view;

    public CalendarPresenter(CalendarModel model, CalendarViewImpl view) {
        System.out.println("CalendarPresenter created");
        _model = model;
        _view = view;
        _view.addObserver(this);
    }

    @Override
    public void update(String actionName) {

    }
}

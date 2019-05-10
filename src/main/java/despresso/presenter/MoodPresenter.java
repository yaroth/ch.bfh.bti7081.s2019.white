package despresso.presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import despresso.logic.MoodModel;
import despresso.view.MoodViewImpl;

public class MoodPresenter implements ObserverInterface {
    private MoodModel model;
    private MoodViewImpl view;

    public MoodPresenter(MoodModel model, MoodViewImpl view) {
        this.model = model;
        this.view = view;
        this.view.addObserver(this);
    }

    @Override
    public void update(ClickEvent<Button> event) {
        model.doSomething();
    }
}

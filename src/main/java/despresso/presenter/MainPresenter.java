package despresso.presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import despresso.logic.MainModel;
import despresso.view.MainViewImpl;
import despresso.view.ObserverInterface;

public class MainPresenter implements ObserverInterface {

    private MainModel model;
    private MainViewImpl view;

    public MainPresenter(MainModel model, MainViewImpl view) {
        this.model = model;
        this.view = view;
        this.view.addObserver(this);
    }

    @Override
    public void update(ClickEvent<Button> event) {
        System.out.println("MainPresenter update() method executed!");
        System.out.println(event.getSource().getText());
        model.doSomething();
    }
}

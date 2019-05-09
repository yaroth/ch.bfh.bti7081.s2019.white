package despresso.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.presenter.ObserverInterface;

import java.util.ArrayList;
import java.util.List;

public class HomeViewImpl extends VerticalLayout implements SubjectInterface {


    private List<ObserverInterface> listeners = new ArrayList<>();

    public HomeViewImpl() {
        HorizontalLayout line1 = new HorizontalLayout();
        Label label1 = new Label("HOME label 1");
        line1.add(label1);
        HorizontalLayout line2 = new HorizontalLayout();
        Label label2 = new Label("HOME label 2");
        line2.add(label2);
        this.add(line1);
        this.add(line2);

    }

    @Override
    public void removeObserver(ObserverInterface observer) {
        listeners.remove(observer);
    }

    @Override
    public void addObserver(ObserverInterface observer) {
        listeners.add(observer);

    }

}

package despresso.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.CalendarAction;
import despresso.presenter.ObserverInterface;

import java.util.ArrayList;
import java.util.List;

public class CalendarViewImpl extends VerticalLayout implements SubjectInterface {


    private List<ObserverInterface> listeners = new ArrayList<>();
    private Button clickMeBtn, resetBtn;
    private Label label;

    public CalendarViewImpl() {
        HorizontalLayout line1 = new HorizontalLayout();
        label = new Label("let's get ready to see a calendar!");
        line1.add(label);
        HorizontalLayout line2 = new HorizontalLayout();
        clickMeBtn = createButton(CalendarAction.CLICK_ME);
        resetBtn = createButton(CalendarAction.RESET);
        Button btn = new Button(CalendarAction.CLICK_ME);
//        btn.addClickListener()
        line2.add(clickMeBtn, resetBtn);
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

    public void setLabel(String label) {
        this.label.setText(label);
    }

    private Button createButton(String text) {
        return new Button(text, event -> {
            for (ObserverInterface listener : listeners) {
                if (event.getSource().equals(clickMeBtn)) {
                    listener.update(CalendarAction.CLICK_ME);
                } else if (event.getSource().equals(resetBtn)){
                    listener.update(CalendarAction.RESET);
                }
            }
        });
    }
}

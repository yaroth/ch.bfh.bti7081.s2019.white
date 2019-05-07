package despresso.calculator;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.List;

public class CalculatorViewImpl extends VerticalLayout implements CalculatorModel.CalculatorViewSubjectInterface {
    private TextField display = new TextField(); // shows current result
    private Label label = new Label("despresso");

    private List<CalculatorViewObserverInterface> listeners = new ArrayList<CalculatorViewObserverInterface>();

    public CalculatorViewImpl() {
        display.setReadOnly(true);
        add(label);
        add(display);
        HorizontalLayout layout_1 = new HorizontalLayout();
        layout_1.add(createButton("7"));
        layout_1.add(createButton("8"));
        layout_1.add(createButton("9"));
        layout_1.add(createButton("/"));
        add(layout_1);
        HorizontalLayout line2 = new HorizontalLayout();
        line2.add(createButton("4"));
        line2.add(createButton("5"));
        line2.add(createButton("6"));
        line2.add(createButton("*"));
        add(line2);
        HorizontalLayout line3 = new HorizontalLayout();
        line3.add(createButton("1"));
        line3.add(createButton("2"));
        line3.add(createButton("3"));
        line3.add(createButton("-"));
        add(line3);
        HorizontalLayout line4 = new HorizontalLayout();
        line4.add(createButton("0"));
        line4.add(createButton("="));
        line4.add(createButton("C"));
        line4.add(createButton("+"));
        add(line4);
    }

    private Button createButton(String operation) {
        return new Button(operation, event -> {
            for (CalculatorViewObserverInterface listener : listeners)
                listener.buttonClick(event.getSource().getText().charAt(0));
        });
    }

    public void setDisplay(double value) {
        display.setValue(Double.toString(value));
    }

    public void addListener(CalculatorViewObserverInterface listener) {
        listeners.add(listener);
    }
}

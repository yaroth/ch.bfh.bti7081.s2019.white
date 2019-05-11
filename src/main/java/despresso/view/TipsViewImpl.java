package despresso.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import despresso.presenter.ObserverInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TipsViewImpl extends VerticalLayout implements SubjectInterface {


    private List<ObserverInterface> listeners = new ArrayList<>();

    public TipsViewImpl() {
        // Create the accordion
        Accordion accordion = new Accordion();

        // Create the first tab, specify caption when adding
        AccordionPanel accordionPanel1 = new AccordionPanel();
        VerticalLayout verticalLayout = new VerticalLayout();

            // Radio Button Ort
            RadioButtonGroup<String> locationButtonGroup = new RadioButtonGroup<>();
            locationButtonGroup.setLabel("Ort");
            locationButtonGroup.setItems("Zu Hause", "Draussen", "Bei der Arbeit");


        // Radio Button Type
            RadioButtonGroup<String> typeButtonGroup = new RadioButtonGroup<>();
            typeButtonGroup.setLabel("Typ");
            typeButtonGroup.setItems("Körper", "Geist");

        // Radio Button Duration
            RadioButtonGroup<String> durationButtonGroup = new RadioButtonGroup<>();
            durationButtonGroup.setLabel("Dauer");
            durationButtonGroup.setItems("Kurz", "Mittel", "Lang");
        // Add Radiobuttons to panel

        verticalLayout.add(locationButtonGroup, typeButtonGroup, durationButtonGroup);

        accordion.add("Filter Tipps", verticalLayout);

        // Create the second tab, specify caption when adding
        AccordionPanel accordionPanel2 = new AccordionPanel();
        accordionPanel2.setSummaryText("Tippliste");
        accordionPanel2.setContent(new Text("setContent for accordionPanel2"));
            accordion.add(accordionPanel2);








        this.add(accordion);
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

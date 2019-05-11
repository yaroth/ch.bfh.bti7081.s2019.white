package despresso.view;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import despresso.presenter.ObserverInterface;

import java.util.ArrayList;
import java.util.List;

public class TipsViewImpl extends VerticalLayout implements SubjectInterface {

    private List<ObserverInterface> listeners = new ArrayList<>();
    private List<RadioButtonGroup> radioButtonGroupList = new ArrayList<>();

    // Testlabel
    public Label testLabel;


    public TipsViewImpl() {
        // Create the accordion
        Accordion accordion = new Accordion();

        // Create the first tab: Filter Tipps
        AccordionPanel accordionPanel1 = new AccordionPanel();
        VerticalLayout verticalLayout = new VerticalLayout();

            // Radio Button Ort
            RadioButtonGroup<String> locationButtonGroup = new RadioButtonGroup<>();
            radioButtonGroupList.add(locationButtonGroup);
            locationButtonGroup.setLabel("Ort");
            locationButtonGroup.setItems("Zu Hause", "Draussen", "Bei der Arbeit");

        // Radio Button Type
            RadioButtonGroup<String> typeButtonGroup = new RadioButtonGroup<>();
            radioButtonGroupList.add(typeButtonGroup);
            typeButtonGroup.setLabel("Typ");
            typeButtonGroup.setItems("Körper", "Geist");

        // Radio Button Duration
            RadioButtonGroup<String> durationButtonGroup = new RadioButtonGroup<>();
            radioButtonGroupList.add(durationButtonGroup);
            durationButtonGroup.setLabel("Dauer");
            durationButtonGroup.setItems("Kurz", "Mittel", "Lang");

        // For handle Events of all RadioButtonGroups
        for(ObserverInterface listener : listeners) {
            for(RadioButtonGroup eventComponent : radioButtonGroupList){
                eventComponent.addValueChangeListener(event -> listener.update((ClickEvent<Button>) event)); // This cast ish shit!!! Check other option <---------------------------------------------------------------
            }
        }


        // Add Radiobuttons to panel
        verticalLayout.add(locationButtonGroup, typeButtonGroup, durationButtonGroup, testLabel);

        // add Testlabel
        this.testLabel.setText("FirstText");
        verticalLayout.add(testLabel);

        accordion.add("Filter Tipps", verticalLayout);

        // Create the second tab: Tippliste
        AccordionPanel accordionPanel2 = new AccordionPanel();
        accordionPanel2.setSummaryText("Tippliste");
        accordionPanel2.setContent(new Text("setContent for accordionPanel2"));
            accordion.add(accordionPanel2);

        this.add(accordion);
   }


//    private RadioButtonGroup<String> createRadioButtonGroup(String label, String[] values) {
//
//        RadioButtonGroup<String> newRadioButtonGroup = new RadioButtonGroup<>();
//        newRadioButtonGroup.setLabel(label);
//        newRadioButtonGroup.setItems(values);
//        for (ObserverInterface listener : listeners) {
//            newRadioButtonGroup.addValueChangeListener(event -> listener.update(event));
//        }
//        return null;
//    }


    @Override
    public void removeObserver(ObserverInterface observer) {
        listeners.remove(observer);
    }

    @Override
    public void addObserver(ObserverInterface observer) {
        listeners.add(observer);

    }

}

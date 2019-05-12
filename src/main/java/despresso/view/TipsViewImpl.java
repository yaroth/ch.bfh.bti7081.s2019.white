package despresso.view;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import despresso.logic.Tip;
import despresso.presenter.ObserverInterface;

import java.util.ArrayList;
import java.util.List;

public class TipsViewImpl extends VerticalLayout implements SubjectInterface {

    private List<ObserverInterface> listeners = new ArrayList<>();
    private List<RadioButtonGroup> radioButtonGroupList = new ArrayList<>();
    private ArrayList<Tip> tipList = new ArrayList<>();

    // Testlabel
    public Label testLabel = new Label();

    public TipsViewImpl() {
        // Create the accordion
        Accordion accordion = new Accordion();


        // Create the first tab: Tippliste
            AccordionPanel accordionPanel2 = new AccordionPanel();
            accordionPanel2.setSummaryText("Tippliste");
            accordionPanel2.setContent(new Text("setContent for accordionPanel2"));

            Grid<Tip> tipGrid = new Grid<>(Tip.class);
            tipGrid.setItems(this.tipList);

            accordionPanel2.addContent(tipGrid);
            accordionPanel2.setOpened(false);
            accordion.add(accordionPanel2);

        // Create the second tab: Filter Tipps
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
            verticalLayout.add(locationButtonGroup, typeButtonGroup, durationButtonGroup);


            // Confirm Filter Button
            Button okButton = createButton("OK");
            verticalLayout.add(okButton);

            // add Testlabel
            this.testLabel.setText("FirstText");
            verticalLayout.add(testLabel);

            accordion.add("Filter Tipps", verticalLayout);


        // Add accordion to TipViewImpl
        this.add(accordion);
   }


    private Button createButton(String text) {
        return new Button(text, event -> {
            System.out.println("Button in TipsView clicked");
            System.out.println(listeners);
            for (ObserverInterface listener : listeners)
                listener.update(event);
        });
    }

    public void setTipList(ArrayList<Tip> tipList) {
        this.tipList = tipList;
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

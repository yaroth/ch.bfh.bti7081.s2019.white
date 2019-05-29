package despresso.view;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.logic.Tip;
import despresso.presenter.TipObserverInterface;

import java.util.ArrayList;
import java.util.List;

@UIScope
@SpringComponent
public class TipsViewImpl extends VerticalLayout implements SubjectTipInterface {

    private List<TipObserverInterface> listeners = new ArrayList<>();
    private ArrayList<Tip> tipList = new ArrayList<>();

    // Create components which have to be updated
    private Checkbox angerCheckbox;
    private Checkbox anxietyCheckbox;
    private Checkbox disgustCheckbox;
    private Checkbox sadnessCheckbox;
    private Checkbox fearCheckbox;

    private RadioButtonGroup<String> locationButtonGroup;
    private RadioButtonGroup<String> durationButtonGroup;
    private RadioButtonGroup<String> typeButtonGroup;

    private Button okButton;
    private Button cancelButton;

    private Grid<Tip> tipGrid = new Grid<>();

    public TipsViewImpl() {
        System.out.println("TipsViewImpl created");
        // Create the accordion
        Accordion accordion = new Accordion();

        // Create the first tab: Tippliste
            AccordionPanel accordionPanel2 = new AccordionPanel();
            accordionPanel2.setSummaryText("Tippliste");
            //accordionPanel2.setContent(new Text("Tippliste"));

            this.tipGrid = new Grid<>(Tip.class);
            this.tipGrid.setWidth("800px");
            tipGrid.setItems(this.tipList);

            accordionPanel2.addContent(tipGrid);
            accordionPanel2.setOpened(false);
            accordion.add(accordionPanel2);

        // Create the second tab: Filter Tipps
            AccordionPanel accordionPanel1 = new AccordionPanel();
            VerticalLayout verticalLayout = new VerticalLayout();

            // Feeling Check boxes
            this.angerCheckbox = new Checkbox("Anger");
            this.disgustCheckbox = new Checkbox("Disgust");
            this.anxietyCheckbox = new Checkbox("Anxiety");
            this.sadnessCheckbox = new Checkbox("Sadness");
            this.fearCheckbox = new Checkbox("Fear");

            verticalLayout.add(angerCheckbox, disgustCheckbox, anxietyCheckbox, sadnessCheckbox, fearCheckbox);

        // Radio Button Location
            this.locationButtonGroup = new RadioButtonGroup<>();
            locationButtonGroup.setLabel("Ort");
            locationButtonGroup.setItems("Zu Hause", "Draussen", "Bei der Arbeit");

            // Radio Button Type
            this.typeButtonGroup = new RadioButtonGroup<>();
            typeButtonGroup.setLabel("Typ");
            typeButtonGroup.setItems("Körper", "Geist");

            // Radio Button Duration
            this.durationButtonGroup = new RadioButtonGroup<>();
            durationButtonGroup.setLabel("Dauer");
            durationButtonGroup.setItems("Kurz", "Mittel", "Lang");


            // Add Radiobuttons to panel
            verticalLayout.add(locationButtonGroup, typeButtonGroup, durationButtonGroup);

            // Confirm Filter Button
            this.okButton = new Button("OK");

            // Candel Button
            this.cancelButton = new Button("Reset all Filters");

            verticalLayout.add(okButton, cancelButton);

            accordion.add("Filter Tipps", verticalLayout);


        // Add accordion to TipViewImpl
        this.add(accordion);
   }



    public void setTipList(ArrayList<Tip> tipList) {
        this.tipList = tipList;
        this.tipGrid.setItems(this.tipList);
    }


    @Override
    public void removeObserver(TipObserverInterface observer) {
        listeners.remove(observer);
    }

    @Override
    public void addObserver(TipObserverInterface observer) {
        System.out.println("TipsViewImpl.addObserver executed");
        listeners.add(observer);
        System.out.println(listeners);

        for(TipObserverInterface listener : listeners){
            this.angerCheckbox.addValueChangeListener(event -> listener.updateFeelingAnger());
            this.disgustCheckbox.addValueChangeListener(event -> listener.updateFeelingDisgust());
            this.anxietyCheckbox.addValueChangeListener(event -> listener.updateFeelingAnxiety());
            this.sadnessCheckbox.addValueChangeListener(event -> listener.updateFeelingSadness());
            this.fearCheckbox.addValueChangeListener(event -> listener.updateFeelingFear());
            this.locationButtonGroup.addValueChangeListener(event -> listener.updateLocation());
            this.typeButtonGroup.addValueChangeListener(event -> listener.updateType());
            this.durationButtonGroup.addValueChangeListener(event -> listener.updateDuration());
            this.okButton.addClickListener(event -> listener.updateOk());

        }


    }

}

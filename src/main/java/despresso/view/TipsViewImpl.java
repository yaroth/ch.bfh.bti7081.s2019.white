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
import despresso.logic.TipDuration;
import despresso.logic.TipLocation;
import despresso.logic.TipType;
import despresso.presenter.TipObserverInterface;

import java.util.ArrayList;
import java.util.List;

@UIScope
@SpringComponent
public class TipsViewImpl extends VerticalLayout implements SubjectInterface<TipObserverInterface> {

    private List<TipObserverInterface> listeners = new ArrayList<>();
    private ArrayList<Tip> tipList = new ArrayList<>();

    // Create components which have to be updated
    private Checkbox angerCheckbox;
    private Checkbox anxietyCheckbox;
    private Checkbox disgustCheckbox;
    private Checkbox sadnessCheckbox;
    private Checkbox fearCheckbox;

    private RadioButtonGroup<TipLocation> locationButtonGroup;
    private RadioButtonGroup<TipDuration> durationButtonGroup;
    private RadioButtonGroup<TipType> typeButtonGroup;

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
            locationButtonGroup.setItems(TipLocation.ATHOME, TipLocation.ATWORK, TipLocation.OUTDOOR);

            // Radio Button Type
            this.typeButtonGroup = new RadioButtonGroup<>();
            typeButtonGroup.setLabel("Typ");
            typeButtonGroup.setItems(TipType.BODY, TipType.MIND);

            // Radio Button Duration
            this.durationButtonGroup = new RadioButtonGroup<>();
            durationButtonGroup.setLabel("Dauer");
            durationButtonGroup.setItems(TipDuration.SHORT, TipDuration.MEDIUM, TipDuration.LONG);


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
            this.cancelButton.addClickListener(event -> listener.updateCancel());
        }


    }

    public boolean getAnger() {
        return this.angerCheckbox.getValue();
    }

    public boolean getDisgust() {
        return this.disgustCheckbox.getValue();
    }

    public boolean getAnxiety() {
        return this.disgustCheckbox.getValue();
    }

    public boolean getSadness() {
        return this.sadnessCheckbox.getValue();
    }

    public boolean getFear() {
        return this.fearCheckbox.getValue();
    }

    public TipDuration getDuration() {
        return this.durationButtonGroup.getValue();
    }

    public TipType getTipType() {
        return this.typeButtonGroup.getValue();
    }

    public TipLocation getTipLocation() {
        return this.locationButtonGroup.getValue();
    }

    public void clearAnger() {
        this.angerCheckbox.clear();
    }

    public void clearDisgust() {
        this.disgustCheckbox.clear();
    }

    public void clearAnxiety() {
        this.anxietyCheckbox.clear();
    }

    public void clearSadness() {
        this.sadnessCheckbox.clear();
    }

    public void clearFear() {
        this.fearCheckbox.clear();
    }

    public void clearDuration() {
        this.durationButtonGroup.clear();
    }

    public void clearType() {
        this.typeButtonGroup.clear();
    }

    public void clearLocation() {
        this.locationButtonGroup.clear();
    }

}

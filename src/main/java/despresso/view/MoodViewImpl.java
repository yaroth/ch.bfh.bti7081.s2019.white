package despresso.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import despresso.presenter.MoodObserverInterface;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.presenter.ObserverInterface;
import org.vaadin.zhe.PaperRangeSlider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@UIScope
@SpringComponent
public class MoodViewImpl extends VerticalLayout implements SubjectInterface<MoodObserverInterface> {

    private String selectedMood = "None";
    private int moodSliderValue = -1;

    private List<MoodObserverInterface> listeners = new ArrayList<>();
    private VerticalLayout mainLayout = new VerticalLayout();
    private VerticalLayout previousLayout = mainLayout;

    private Button saveButton;
    private Button undoButton;
    private Button specifyButton;
    private Button confirmAccuracyButton;

    public MoodViewImpl() {
        // Initialize view
        initView();
    }

    // Create standard button, event passes string value of the button
    private Button createButton(String command) {
        return new Button(command, this::registerObject);
    }

    private void registerObject(ClickEvent event) {
        for (MoodObserverInterface listener : listeners) {
            if (event.getSource().equals(saveButton)) {
                listener.saveButton();
            } else if (event.getSource().equals(undoButton)) {
                listener.undoButton();
            } else if (event.getSource().equals(specifyButton)) {
                listener.specifyButton();
            } else if (event.getSource().equals(confirmAccuracyButton)) {
                listener.confirmAccuracyButton();
            }
        }
    }

    // Create button to select mood
    private Button createMoodButton(String value) {
        return new Button(value, event -> {
            selectedMood = value;
            for (MoodObserverInterface listener : listeners)
                listener.moodSelectionButton();
        });
    }

    @Override
    public void removeObserver(MoodObserverInterface observer) {
        listeners.remove(observer);
    }

    @Override
    public void addObserver(MoodObserverInterface observer) {
        listeners.add(observer);

    }

    ///////////////////////////////////////////////////////
    //  Subviews

    // The main view when first accessing mood view
    private void initView() {

        HorizontalLayout line1 = new HorizontalLayout();
        line1.add(new Label("How is your mood now?"));

        HorizontalLayout line2 = new HorizontalLayout();

        line2.add(
            createMoodButton("Good"),
            createMoodButton("Bad")
        );

        this.mainLayout.add(line1);
        this.mainLayout.add(line2);
        this.previousLayout = mainLayout;
        this.add(mainLayout);
    }

    // View after choosing a mood
    public void showMoodOptions(String newMood) {
        VerticalLayout newLayout = new VerticalLayout();
        HorizontalLayout line1 = new HorizontalLayout();

        line1.add(new Label("Your chose " + newMood + "!"));

        HorizontalLayout line2 = new HorizontalLayout();

        saveButton = createButton("Save");
        undoButton = createButton("Undo");
        specifyButton = createButton("Specify");


        line2.add(
            saveButton,
            undoButton,
            specifyButton
        );

        newLayout.add(line1, line2);

        this.replace(mainLayout, newLayout);
        this.previousLayout = mainLayout;
        mainLayout = newLayout;
    }

    // View to select a more specific mood
    public void showSpecificMoods(ArrayList<String> specificMoods) {
        VerticalLayout newLayout = new VerticalLayout();
        HorizontalLayout line1 = new HorizontalLayout();

        line1.add(new Label("How is your mood really?"));

        HorizontalLayout line2 = new HorizontalLayout();

        for(String mood : specificMoods) {
            line2.add(createMoodButton(mood));
        }

        newLayout.add(line1, line2);

        this.replace(mainLayout, newLayout);
        this.previousLayout = mainLayout;
        mainLayout = newLayout;
    }

    // Mood already specific, grade with slider
    public void showMoodSlider() {
        VerticalLayout newLayout = new VerticalLayout();

        HorizontalLayout line1 = new HorizontalLayout();
        line1.add(new Label("How accurately does this mood describe your feelings?"));

        HorizontalLayout line2 = new HorizontalLayout();
        PaperRangeSlider moodSlider = createMoodSlider();
        moodSliderValue = (int) moodSlider.getValueMax();
        line2.add(moodSlider);

        HorizontalLayout line3 = new HorizontalLayout();

        confirmAccuracyButton = createButton("ConfirmAccuracy");
        line3.add( confirmAccuracyButton );

        newLayout.add(line1, line2, line3);

        this.replace(mainLayout, newLayout);
        this.previousLayout = mainLayout;
        mainLayout = newLayout;
    }

    // Reset the mood view
    public void resetView() {
        this.remove(mainLayout);
        mainLayout = new VerticalLayout();
        this.initView();
    }

    public void undoMoodSelection() {
        this.replace(mainLayout, previousLayout);
        mainLayout = previousLayout;
    }

    public String getSelectedMood() {
        return selectedMood;
    }

    public int getMoodSliderValue() {
        return moodSliderValue;
    }

    // Creates a new mood slider
    private PaperRangeSlider createMoodSlider() {
        PaperRangeSlider slider = new PaperRangeSlider(1, 10, 0, 10);
        slider.setStep(1);
        slider.setSingleSlider(true);
        slider.addMaxValueChangeListener(
                (ComponentEventListener<PaperRangeSlider.MaxValueChangeEvent>) event -> {
            moodSliderValue = (int) event.getValueMax();
            for (MoodObserverInterface listener : listeners)
                listener.moodSlider();
        });
        return slider;
    }
}

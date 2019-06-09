package despresso.view;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.Views;
import despresso.presenter.ObserverInterface;
import org.vaadin.zhe.PaperRangeSlider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@UIScope
@SpringComponent
public class MoodViewImpl extends VerticalLayout implements SubjectInterface<ObserverInterface> {

    private String selectedMood = "None";
    private int moodSliderValue = -1;

    private List<ObserverInterface> listeners = new ArrayList<>();
    private VerticalLayout mainLayout = new VerticalLayout();
    private VerticalLayout previousLayout = mainLayout;
    PaperRangeSlider paperRangeSlider;

    public MoodViewImpl() {
        // Initialize view
        initView();
    }

    // Create standard button, event passes string value of the button
    private Button createButton(String command) {
        return new Button(ACTIONS.get(command), event -> {
            for (ObserverInterface listener : listeners)
                listener.update(command);
        });
    }

    // Create button to select mood
    private Button createMoodButton(String value) {
        return new Button(value, event -> {
            selectedMood = value;
            for (ObserverInterface listener : listeners)
                listener.update("Mood");
        });
    }

    @Override
    public void removeObserver(ObserverInterface observer) {
        listeners.remove(observer);
    }

    @Override
    public void addObserver(ObserverInterface observer) {
        listeners.add(observer);

    }

    ///////////////////////////////////////////////////////
    //  Subviews

    // The main view when first accessing mood view
    public void initView() {

        HorizontalLayout line1 = new HorizontalLayout();
        HorizontalLayout line2 = new HorizontalLayout();
        Label moodTitle = new Label("How are you feeling today?");
        line1.add(moodTitle);
        line2.add(new Label("Bad "), moodSliderHomeView(), new Label("Good "));

        this.mainLayout.add(line1);
        this.mainLayout.add(line2);
        this.previousLayout = mainLayout;
        this.add(mainLayout);
    }

    public PaperRangeSlider moodSliderHomeView() {

        // Create a horizontal slider
        paperRangeSlider = new PaperRangeSlider(-1, 1, 0, 0);
        paperRangeSlider.setStep(1);
        paperRangeSlider.setSingleSlider(true);
        //Add listener
        registerMainMoodSlider(paperRangeSlider);

        return paperRangeSlider;
    }

    // View after choosing a mood
    public void showMoodOptions(String newMood) {
        VerticalLayout newLayout = new VerticalLayout();
        HorizontalLayout line1 = new HorizontalLayout();

        line1.add(new Label("Your chose " + newMood + "!"));

        HorizontalLayout line2 = new HorizontalLayout();

        line2.add(
            createButton("Save"),
            createButton("Undo"),
            createButton("Specify")
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
        line3.add(createButton("ConfirmAccuracy"));

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
                    for (ObserverInterface listener : listeners)
                        listener.update("MoodSlider");
                });
        return slider;
    }

    private void registerMainMoodSlider(PaperRangeSlider paperRangeSlider) {
        paperRangeSlider.addMaxValueChangeListener(
                (ComponentEventListener<PaperRangeSlider.MaxValueChangeEvent>) event -> {
                    double mood = event.getValueMax();
                    if (mood < 0) {
                        selectedMood = "Bad";
                    } else if (mood > 0) {
                        selectedMood = "Good";
                    }
                    for (ObserverInterface listener : listeners)
                        listener.update("Mood");
                });
    }

    // Immutable map to translate commands to button labels
    private Map<String, String> ACTIONS = Map.ofEntries(
            entry("Save", "Save"),
            entry("Undo", "Undo"),
            entry("Specify", "Specify"),
            entry("ConfirmAccuracy", "Confirm")
    );
}

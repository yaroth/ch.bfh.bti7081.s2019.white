package despresso.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.presenter.ObserverInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class MoodViewImpl extends VerticalLayout implements SubjectInterface {

    private String selectedMood = "None";
    private List<ObserverInterface> listeners = new ArrayList<>();
    private VerticalLayout mainLayout = new VerticalLayout();
    private VerticalLayout previousLayout = mainLayout;

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
        line2.add(new Label("Slider here"));

        HorizontalLayout line3 = new HorizontalLayout();
        line3.add(createButton("Confirm"));

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

    // Immutable map to translate commands to button labels
    private static Map<String, String> ACTIONS = Map.ofEntries(
            entry("Save", "Shave"),
            entry("Undo", "Undo"),
            entry("Specify", "Specify"),
            entry("Confirm", "Confirm")
    );
}

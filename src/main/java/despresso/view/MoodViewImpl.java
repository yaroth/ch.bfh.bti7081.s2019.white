package despresso.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class MoodViewImpl extends View {

    private Label label;

    public MoodViewImpl() {
        HorizontalLayout line1 = new HorizontalLayout();
        label = new Label("Click the button below");
        line1.add(label);
        HorizontalLayout line2 = new HorizontalLayout();
        Button btn1 = createButton("click me");
        Button btn2 = createButton("reset");
        Button btn = new Button("test");
//        btn.addClickListener()
        line2.add(btn1, btn2);
        this.add(line1);
        this.add(line2);

    }
}

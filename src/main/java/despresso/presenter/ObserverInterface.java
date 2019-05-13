package despresso.presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import javafx.scene.control.RadioButton;

import javax.swing.event.ChangeEvent;

public interface ObserverInterface {

    void update(ClickEvent<Button> event);

    void updateFromChangeEvent(HasValue.ValueChangeEvent event);

}
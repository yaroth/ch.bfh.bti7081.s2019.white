package despresso.presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

public interface ObserverInterface {

    void update(ClickEvent<Button> event, String btnLabel);
}
package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class HomeModel {


    public void doSomething() {
        System.out.println("getClickMeResult() method in HomeModel executed!");
    }

    public String closeCalendarEntry() {
        System.out.println("Close CalendarEntry");
        return("CalendarEntry closed");
    }
}

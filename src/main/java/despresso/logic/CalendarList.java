package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.vaadin.stefan.fullcalendar.Entry;

import java.time.LocalDateTime;
import java.util.ArrayList;


@UIScope
@SpringComponent
public class CalendarList extends ArrayList<CalendarEntry> {

    public void addCalendarEntry(String userId, LocalDateTime startTime, LocalDateTime endTime, String title, String description, String color, boolean isDone){
        this.add(new CalendarEntry(userId, startTime, endTime, title, description, color, isDone));
    }

    public void updateCalendarEntry(Entry entry){
        //**n unique key fehlt noch... evtl. ID, wenn DB angebunden
    }
}

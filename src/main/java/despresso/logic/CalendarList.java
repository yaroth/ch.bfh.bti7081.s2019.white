package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.persistence.CalendarRepository;
import org.vaadin.stefan.fullcalendar.Entry;

import java.time.LocalDateTime;
import java.util.ArrayList;


@UIScope
@SpringComponent
public class CalendarList extends ArrayList<CalendarEntry> {

    private CalendarRepository _repository;

    public CalendarList(){
        _repository = new CalendarRepository();
    }

    public void addCalendarEntry(String userId, LocalDateTime startTime, LocalDateTime endTime, String title, String description, String color, boolean isDone){
        CalendarEntry newEntry = new CalendarEntry(userId, startTime, endTime, title, description, color, isDone);
        this.add(newEntry);
        _repository.insert(newEntry);
    }

    public void updateCalendarEntry(Entry entry){
        //**n unique key fehlt noch... evtl. ID, wenn DB angebunden

    }
}

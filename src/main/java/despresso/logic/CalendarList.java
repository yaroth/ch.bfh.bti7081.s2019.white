package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.persistence.CalendarRepository;
import org.vaadin.stefan.fullcalendar.Entry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;


@UIScope
@SpringComponent
public class CalendarList extends ArrayList<CalendarEntry> {

    private CalendarRepository _repository;

    public CalendarList(){
        _repository = new CalendarRepository();
    }

    public void addCalendarEntry(String id, LocalDateTime startTime, LocalDateTime endTime, String title, String description, String color, boolean isDone){
        CalendarEntry newEntry = new CalendarEntry(id, startTime, endTime, title, description, color, isDone);
        this.add(newEntry);
        _repository.insert(newEntry);
    }

    public void updateCalendarEntry(Entry entry){
        this.stream().filter(m -> m.getId().equals(entry.getId())).findFirst().ifPresent(currentEntry -> _repository.update(currentEntry));
    }

    public void deleteCalendarEntry(Entry entry){
        this.stream().filter(m -> m.getId().equals(entry.getId())).findFirst().ifPresent(currentEntry -> _repository.delete(currentEntry));
    }

    public Optional<CalendarEntry> getNextCalendarEntry(){
        LocalDateTime now = LocalDateTime.now();
        return this.stream().filter(m -> m.getStart().isAfter(now) && !m.getIsDone()).findFirst();
    }
}

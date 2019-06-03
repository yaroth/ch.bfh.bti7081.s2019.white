package despresso.logic;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CalendarList extends ArrayList<CalendarEntry> {

    public void addCalendarEntry(String userId, LocalDateTime startTime, LocalDateTime endTime, String title, String description, String color, boolean isDone){
        this.add(new CalendarEntry(userId, startTime, endTime, title, description, color, isDone));
    }

}

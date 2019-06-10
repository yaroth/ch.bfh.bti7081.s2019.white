package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.vaadin.stefan.fullcalendar.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

@UIScope
@SpringComponent
public class CalendarModel {

    private FullCalendar _calendar;
    private Scheduler _scheduler;

    public CalendarModel(){

    }

    public java.util.List<CalendarEntry> sortCalendarEntriesByDateNow(CalendarList _calendarList) {
        List<CalendarEntry> calendarEntries = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        for (CalendarEntry entry: _calendarList) {
            if (date.isBefore(entry.getStart()))
                calendarEntries.add(entry);
        }

        calendarEntries.sort(Comparator.comparing(CalendarEntry::getStart));
        return calendarEntries;
    }
}

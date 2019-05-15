package despresso.logic;

import org.vaadin.stefan.fullcalendar.*;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public class CalendarModel {

    private FullCalendar _calendar;
    private Scheduler _scheduler;

    public CalendarModel(){

    }

    public FullCalendar getInitCalendar(){
        _calendar = FullCalendarBuilder.create().withScheduler().build();
        ((Scheduler)_calendar).setSchedulerLicenseKey("");

        return _calendar;
    }

    private void addResource(String title, String color) {
        Resource resource = new Resource(null, title, color);

        /*
        _calendar..addResource(resource);

        // When we want to link an entry with a resource, we need to use ResourceEntry
        // (a subclass of Entry)
        ResourceEntry entry = new ResourceEntry(null, title, start.atStartOfDay(), start.plusDays(days).atStartOfDay(), true, true, color, "Some description...");
        entry.setResource(resource);
        calendar.addEntry(entry);
    }   */
    }
}

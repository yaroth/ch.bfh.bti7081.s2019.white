package despresso.logic;

import org.vaadin.stefan.fullcalendar.*;

import java.awt.*;
import java.time.LocalDateTime;

public class CalendarModel {

    private FullCalendar _calendar;

    public CalendarModel(){
        _calendar = FullCalendarBuilder.create().withScheduler().build();

    }

    public void createCalendarEntry(String title, String color, LocalDateTime startTime, LocalDateTime endTime){

        // scheduler options
        //((Scheduler) calendar).setSchedulerLicenseKey();

        Resource resource = new Resource(null, title, color);
        _calendar.addEntry(new Entry()
        {

        });

// When we want to link an entry with a resource, we need to use ResourceEntry
// (a subclass of Entry)
        ResourceEntry entry = new ResourceEntry(null, title, startTime, endTime, true, true, color, "Some description...");
        entry.setResource(resource);
        _calendar.addEntry(entry);
    }
}

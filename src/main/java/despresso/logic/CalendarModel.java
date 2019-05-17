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
        ((Scheduler)_calendar).setSchedulerLicenseKey(Scheduler.GPL_V3_LICENSE_KEY);


        return _calendar;
    }

    private void addResource(String title, String color) {
        //Resource resource = new Resource(null, title, color);


    }
}

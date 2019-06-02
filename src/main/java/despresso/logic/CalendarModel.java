package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.vaadin.stefan.fullcalendar.*;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@UIScope
@SpringComponent
public class CalendarModel {

    private FullCalendar _calendar;
    private Scheduler _scheduler;

    public CalendarModel(){

    }
}

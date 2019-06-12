package despresso.logic;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

import static org.junit.Assert.*;

public class CalendarEntryTest {

    LocalDateTime now = LocalDateTime.now();

    CalendarEntry fullEntry = new CalendarEntry(20, "20", now, now, "test1", "test1", "blue", false);


    @Test
    public void getId() {
        assertTrue(fullEntry.getId() == 20);
    }

    @Test
    public void getStart() {
        assertTrue(fullEntry.getStart() == now);
    }

    @Test
    public void getEnd() {
        assertTrue(fullEntry.getEnd() == now);
    }

    @Test
    public void getTitle() {
        assertTrue(fullEntry.getTitle() == "test1");
    }

    @Test
    public void getDescription() {
        assertTrue(fullEntry.getDescription() == "test1");
    }

    @Test
    public void getColor() {
        assertTrue(fullEntry.getColor() == "blue");
    }

    @Test
    public void getIsDone() {
        assertFalse(fullEntry.getIsDone());
    }
}
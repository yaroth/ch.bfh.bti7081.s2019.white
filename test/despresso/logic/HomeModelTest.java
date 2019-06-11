package despresso.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class HomeModelTest {
    HomeModel model = new HomeModel();

    @Test
    public void doSomething() {
        //nothing to test yet
    }

    @Test
    public void closeCalendarEntry() {
        System.out.println(model.closeCalendarEntry());

        assertNotNull(model.closeCalendarEntry());
    }
}
import despresso.persistence.*;
import org.junit.Test;

//not working yet with memoryDB
public class RepositoryTest {
    //______IMPORTANT_______
    //The following tests are only possible when the application is running.

    private CalendarRepository calendar;
    private TipRepository tip;
    private UserRepository user;

    @Test
    public void testCalendarRepository(){
        //toDo: Code when repository is properly implemented
    }

    @Test
    public void testMoodRepository(){
        //toDo: Code when repository is properly implemented
    }

    @Test
    public void testTipRepository(){
        //toDo: Code when repository is properly implemented
    }

    @Test
    public void testUserRepository(){
        //test get all Users in predefined Database

        System.out.println(user.getByID(1).toString());
    }
}

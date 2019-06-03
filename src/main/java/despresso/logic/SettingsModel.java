package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.DataType;

import java.time.LocalDate;
import java.util.List;

@UIScope
@SpringComponent
public class SettingsModel {

    H2DBConnector h2DBConnector = new H2DBConnector();

    public String getClickMeResult() {
        return "Button clicked!";
    }

    public String getResetResult() {
        return "Test reset!";
    }

    public String saveSettings(String checked) {
        System.out.println(checked + " was chosen.");
        //do the actual changes
        System.out.println("Settings saved");
        return ("Settings Saved!");
    }

    public String deleteData() {
        //do the actual changes

        // TODO: TESTING purpose *remove these lines* once testing is done ********************************************.
        DataTypeInterface user = h2DBConnector.getById(DataType.USER, 1);
        System.out.println("getbyid: " + user);

        User lukas = new User("Lukas", "Zoller", LocalDate.of(1981, 5, 25));
        h2DBConnector.insert(lukas);

        List<DataTypeInterface> userList = h2DBConnector.getAll(DataType.USER);
        System.out.println("Added new user");
        System.out.println(userList);

        DataTypeInterface newLukas = h2DBConnector.getById(DataType.USER, 3);
        if (newLukas != null) {
            lukas = ((User) newLukas);
            lukas.setDob(LocalDate.of(1981, 5, 11));
            h2DBConnector.update(lukas);
            userList = h2DBConnector.getAll(DataType.USER);
            System.out.println("Modified user with id=3");
            System.out.println(userList);

            h2DBConnector.delete(lukas);

            userList = h2DBConnector.getAll(DataType.USER);
            System.out.println("Deleted user with id=3");
            System.out.println(userList);
        }
        // TODO: END of *remove these lines *******************************************************

        return ("Personal h2DBConnector deleted!");
    }

    public String deleteAccount() {
        //do the actual changes
        System.out.println("Account deleted");
        return ("Account deleted!");
    }
}

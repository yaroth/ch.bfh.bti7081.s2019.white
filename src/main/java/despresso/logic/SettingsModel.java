package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.DataType;
import despresso.persistence.UserRepository;

import java.time.LocalDate;
import java.util.List;

@UIScope
@SpringComponent
public class SettingsModel {

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

        UserRepository userRepository = new UserRepository();
        // TODO: TESTING purpose *remove these lines* once testing is done ********************************************.
        User user = userRepository.getByID(1);
        System.out.println("get by id: " + user);

        User lukas = new User("Lukas", "Zoller", LocalDate.of(1981, 5, 25));
        userRepository.insert(lukas);

        List<User> userList = userRepository.getAll();
        System.out.println("Added new user");
        System.out.println(userList);

        DataTypeInterface newLukas = userRepository.getByID(3);
        if (newLukas != null) {
            lukas = ((User) newLukas);
            lukas.setDob(LocalDate.of(1981, 5, 11));
            userRepository.update(lukas);
            userList = userRepository.getAll();
            System.out.println("Modified user with id=3");
            System.out.println(userList);

            userRepository.delete(lukas);

            userList = userRepository.getAll();
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

package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

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
        return ("Personal data deleted!");
    }

    public String deleteAccount() {
        //do the actual changes
        System.out.println("Account deleted");
        return ("Account deleted!");
    }
}

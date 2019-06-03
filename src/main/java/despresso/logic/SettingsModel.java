package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class SettingsModel {

    H2DBConnector database = new H2DBConnector();

    public String getClickMeResult() {
        return "Button clicked!";
    }

    public String getResetResult() {
        return "Test reset!";
    }

    public String saveSettings(String checked){
        System.out.println(checked + " was chosen.");
        //do the actual changes
        System.out.println("Settings saved");
        return("Settings Saved!");
    }

    public String deleteData() {
        //do the actual changes
        String teststring = database.databaseGet("SELECT * FROM user");
        System.out.println("Personal Database deleted");
        System.out.println(teststring);
        return("Personal Database deleted!");
    }

    public String deleteAccount() {
        //do the actual changes
        System.out.println("Account deleted");
        return("Account deleted!");
    }
}

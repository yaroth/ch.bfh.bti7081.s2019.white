package despresso.logic;

public class SettingsModel {

    public String doSomething() {
        return "Button clicked!";
    }

    public String doSomethingElse() {
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
        System.out.println("Personal Database deleted");
        return("Personal Database deleted!");
    }

    public String deleteAccount() {
        //do the actual changes
        System.out.println("Account deleted");
        return("Account deleted!");
    }
}

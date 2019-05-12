package despresso.logic;

public class SettingsModel {

    public String doSomething() {
        return "Button clicked!";
    }

    public String doSomethingElse() {
        return "Test reset!";
    }

    public String saveSettings(){
        System.out.println("Settings saved");
        return("Settings Saved!");
    }

    public String deleteData() {
        System.out.println("Personal Database deleted");
        return("Personal Database deleted!");
    }

    public String deleteAccount() {
        System.out.println("Account deleted");
        return("Account deleted!");
    }
}

package despresso.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class SettingsModelTest {

    SettingsModel model = new SettingsModel();

    @Test
    public void getClickMeResult() {
        assertNotNull(model.getClickMeResult());
    }

    @Test
    public void getResetResult() {
        assertNotNull(model.getResetResult());
    }

    @Test
    public void saveSettings() {
        assertTrue(model.saveSettings("Yes") == "Settings Saved!");
    }

    @Test
    public void deleteData() {
        assertTrue(model.deleteData() == "Personal data deleted!");
    }

    @Test
    public void deleteAccount() {
        assertTrue(model.deleteAccount() == "Account deleted!");
    }
}
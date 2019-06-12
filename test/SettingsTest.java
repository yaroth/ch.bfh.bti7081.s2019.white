import despresso.logic.SettingsModel;
import despresso.presenter.SettingsPresenter;
import despresso.view.SettingsViewImpl;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class SettingsTest {

    private SettingsViewImpl view = new SettingsViewImpl();
    private SettingsModel model = new SettingsModel();
    private SettingsPresenter presenter = new SettingsPresenter(model, view);

    @Test
    public void testSettings(){

        view.setLabel("test");
        String text = view.getRadiobuttonText();
        Boolean textChange = (text == "Yes");

        presenter.updateDeleteAccountButton();

        assertTrue(textChange);

    }
}

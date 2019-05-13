package despresso.presenter;

import despresso.SettingsAction;
import despresso.logic.SettingsModel;
import despresso.view.SettingsViewImpl;

public class SettingsPresenter implements ObserverInterface {

    private SettingsModel model;
    private SettingsViewImpl view;

    public SettingsPresenter(SettingsModel model, SettingsViewImpl view) {
        this.model = model;
        this.view = view;
        this.view.addObserver(this);
    }

    @Override
    public void update(String actionName) {
        if (actionName.equals(SettingsAction.CLICK_ME)) {
            // here the model is returning some value
            view.setLabel(model.getClickMeResult());
        } else if (actionName.equals(SettingsAction.RESET)) {
            view.setLabel(model.getResetResult());
        }
    }
}

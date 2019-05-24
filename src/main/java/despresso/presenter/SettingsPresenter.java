package despresso.presenter;

import despresso.logic.SettingsModel;
import despresso.view.SettingsViewImpl;

public class SettingsPresenter implements SettingsObserverInterface {

    private SettingsModel model;
    private SettingsViewImpl view;
    private String deleteWhat = "";

    public SettingsPresenter(SettingsModel model, SettingsViewImpl view) {
        this.model = model;
        this.view = view;
        this.view.addObserver(this);
    }

    @Override
    public void updateSaveButton() {
        view.setLabel(model.saveSettings(view.getRadiobuttonText()));
    }

    @Override
    public void updateDeleteDataButton() {
        view.addConfirmationDialog("Do you really want to delete all your personal saved Data from the Database?");
        deleteWhat = "data";
        System.out.println("Delete Account confirmation window opened");

    }

    @Override
    public void updateDeleteAccountButton() {
        view.addConfirmationDialog("Do you really want to delete your account?");
        deleteWhat="account";
        System.out.println("Delete Account confirmation window opened");
    }

    @Override
    public void updateConfirmButton() {
        if (deleteWhat=="data"){
            view.setLabel(model.deleteData());
        } else if (deleteWhat=="account"){
            view.setLabel(model.deleteAccount());
        } else {
            view.setLabel("not confirmed");
        }
    }

    @Override
    public void updateCancelButton() {
        view.setLabel("not confirmed");
        deleteWhat = "";
    }

    @Override
    public void update(String viewName) {
        view.setLabel("Your personal settings for the despresso-app");
    }
}
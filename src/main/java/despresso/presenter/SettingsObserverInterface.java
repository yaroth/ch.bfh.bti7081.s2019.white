package despresso.presenter;

public interface SettingsObserverInterface extends ObserverInterface {

    //Buttons
    void updateSaveButton();
    void updateDeleteDataButton();
    void updateDeleteAccountButton();

    //Buttons of the Popup-"Confirm-Box"
    void updateConfirmButton();
    void updateCancelButton();
}

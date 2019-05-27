package despresso.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import despresso.Views;
import despresso.logic.SettingsModel;
import despresso.view.SettingsViewImpl;

@UIScope
@SpringComponent
public class SettingsPresenter implements ObserverInterface {

    private SettingsModel model;
    private SettingsViewImpl view;
    private String deleteWhat = "";

    public SettingsPresenter(SettingsModel model, SettingsViewImpl view) {
        this.model = model;
        this.view = view;
        this.view.addObserver(this);
    }

    @Override
    public void update(String string) {
        if (string.equals(Views.SAVE.toString())) {
            view.setLabel(model.saveSettings(view.getRadiobuttonText()));
        } else if (string.equals(Views.DELETE_DATA.toString())) {
            view.addConfirmationDialog("Do you really want to delete all your personal saved Data from the Database?");
            deleteWhat = "data";
            System.out.println("Delete Data confirmation window opened");
        } else if (string.equals(Views.DELETE_ACCOUNT.toString())) {
            view.addConfirmationDialog("Do you really want to delete your account?");
            deleteWhat="account";
            System.out.println("Delete Account confirmation window opened");
        } else if (string.equals(Views.CONFIRM.toString())){
            if (deleteWhat=="data"){
                view.setLabel(model.deleteData());
            } else if (deleteWhat=="account"){
                view.setLabel(model.deleteAccount());
            } else {
                view.setLabel("not confirmed");
            }
        } else {
            view.setLabel("not confirmed");
            deleteWhat = "";
        }
    }
}
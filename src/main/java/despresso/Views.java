package despresso;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.sql.Savepoint;

public enum Views {
    HOME           ("Home", new Icon(VaadinIcon.HOME_O)),
    SETTINGS       ("Settings", new Icon(VaadinIcon.COG_O)),
    MOOD           ("Mood", null),
    CALENDAR       ("Calendar", null),
    TIPS           ("Tips", null),
    SAVE           ("Save", null),
    DELETE_DATA    ("Delete Data", null),
    DELETE_ACCOUNT ("Delete Account", null),
    CONFIRM        ("Confirm", null),
    CANCEL         ("Cancel", null),
    RADIOLOCATION  ("RadioLocation", null),
    RADIODURATION  ("RadioDuration", null),
    RADIOTYPE      ("RadioType", null);


    private String settings;
    private Icon icon;

    Views(String settings, Icon icon) {
        this.settings = settings;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return this.settings;
    }

    public Icon getIcon(){
        return this.icon;
    }
}
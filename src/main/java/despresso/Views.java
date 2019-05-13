package despresso;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public enum Views {
    HOME           ("Home", new Icon(VaadinIcon.HOME_O)),
    SETTINGS       ("Settings", new Icon(VaadinIcon.COG_O)),
    MOOD           ("Mood", null),
    CALENDAR       ("Calendar", null),
    TIPS           ("Tips", null);

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
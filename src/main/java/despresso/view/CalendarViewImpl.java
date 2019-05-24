package despresso.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.dom.ThemeList;
import despresso.presenter.CalendarPresenter;
import despresso.presenter.ObserverInterface;
import org.vaadin.stefan.fullcalendar.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class CalendarViewImpl extends VerticalLayout implements SubjectInterface<ObserverInterface> {

    private static final String[] COLORS = {"tomato", "orange", "dodgerblue", "mediumseagreen", "gray", "slateblue", "violet"};
    private List<ObserverInterface> listeners = new ArrayList<>();
    private Label label;
    private CalendarPresenter _presenter;
    private FullCalendar _calendar;

    public CalendarViewImpl() {

        _calendar = new FullCalendar();
        _calendar.changeView(org.vaadin.stefan.fullcalendar.CalendarViewImpl.AGENDA_DAY);
        _calendar.setNowIndicatorShown(true);
        _calendar.setNumberClickable(true);
        _calendar.setTimeslotsSelectable(true);
        _calendar.setBusinessHours(
                new BusinessHours(LocalTime.of(8, 0), LocalTime.of(17, 0), BusinessHours.DEFAULT_BUSINESS_WEEK),
                new BusinessHours(LocalTime.of(12, 0), LocalTime.of(15, 0), DayOfWeek.SATURDAY)
        );

        // events
        _calendar.addEntryClickedListener(event -> new EventDialog(_calendar, event.getEntry(), false).open());
        _calendar.addTimeslotsSelectedListener((org.vaadin.stefan.fullcalendar.TimeslotsSelectedEvent event) -> {
            Entry entry = new Entry();

            entry.setStart(_calendar.getTimezone().convertToUTC(event.getStartDateTime()));
            entry.setEnd(_calendar.getTimezone().convertToUTC(event.getEndDateTime()));
            entry.setAllDay(event.isAllDay());

            entry.setColor("dodgerblue");
            new EventDialog(_calendar, entry, true).open();
        });

        initBaseLayoutSettings();

        this.add(_calendar);
    }

    @Override
    public void removeObserver(ObserverInterface observer) {
        listeners.remove(observer);
    }

    @Override
    public void addObserver(ObserverInterface observer) {
        listeners.add(observer);
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    private void initBaseLayoutSettings() {
        setSizeFull();
        //_calendar.setHeightByParent();
        _calendar.setHeight(400);
        setFlexStyles(true);
    }

    private void setFlexStyles(boolean flexStyles) {
        if (flexStyles) {
            _calendar.getElement().getStyle().set("flex-grow", "1");
            getElement().getStyle().set("display", "flex");
            getElement().getStyle().set("flex-direction", "column");
        } else {
            _calendar.getElement().getStyle().remove("flex-grow");
            getElement().getStyle().remove("display");
            getElement().getStyle().remove("flex-direction");
        }
    }

    private Resource createResource(Scheduler calendar, String s, String color) {
        Resource resource = new Resource(null, s, color);
        calendar.addResource(resource);
        return resource;
    }

    private void createDayEntry(FullCalendar calendar, String title, LocalDate start, int days, String color) {
        ResourceEntry entry = new ResourceEntry();
        setValues(calendar, entry, title, start.atStartOfDay(), days, ChronoUnit.DAYS, color);
        calendar.addEntry(entry);
    }

    private void createTimedEntry(FullCalendar calendar, String title, LocalDateTime start, int minutes, String color) {
        createTimedEntry(calendar, title, start, minutes, color, (Resource[]) null);
    }

    private void createTimedEntry(FullCalendar calendar, String title, LocalDateTime start, int minutes, String color, Resource... resources) {
        ResourceEntry entry = new ResourceEntry();
        setValues(calendar, entry, title, start, minutes, ChronoUnit.MINUTES, color);
        if (resources != null && resources.length > 0) {
            entry.addResources(Arrays.asList(resources));
        }
        calendar.addEntry(entry);
    }

    private void setValues(FullCalendar calendar, ResourceEntry entry, String title, LocalDateTime start, int amountToAdd, ChronoUnit unit, String color) {
        entry.setTitle(title);
        entry.setStart(start, calendar.getTimezone());
        entry.setEnd(entry.getStartUTC().plus(amountToAdd, unit));
        entry.setAllDay(unit == ChronoUnit.DAYS);
        entry.setColor(color);
    }


    public static class EventDialog extends Dialog {

        EventDialog(FullCalendar calendar, Entry entry, boolean newInstance) {
            setCloseOnEsc(true);
            setCloseOnOutsideClick(true);

            VerticalLayout layout = new VerticalLayout();
            layout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);
            layout.setSizeFull();

            TextField fieldTitle = new TextField("Title");
            fieldTitle.focus();

            ComboBox<String> fieldColor = new ComboBox<>("Color", COLORS);
            TextArea fieldDescription = new TextArea("Description");

            layout.add(fieldTitle, fieldColor, fieldDescription);

            TextField fieldStart = new TextField("Start");
            fieldStart.setReadOnly(true);

            TextField fieldEnd = new TextField("End");
            fieldEnd.setReadOnly(true);

            fieldStart.setValue(calendar.getTimezone().formatWithZoneId(entry.getStartUTC()));
            fieldEnd.setValue(calendar.getTimezone().formatWithZoneId(entry.getEndUTC()));

            Checkbox fieldAllDay = new Checkbox("All day event");
            fieldAllDay.setValue(entry.isAllDay());
            fieldAllDay.setReadOnly(true);

            layout.add(fieldStart, fieldEnd, fieldAllDay);

            if (entry instanceof ResourceEntry && ((ResourceEntry) entry).getResource().isPresent()) {
                TextArea fieldResource = new TextArea("Assigned resources");
                fieldResource.setReadOnly(true);
                fieldResource.setValue(((ResourceEntry) entry).getResources().stream().map(Resource::getTitle).collect(Collectors.joining(", ")));
                layout.add(fieldResource);
            }

            Binder<Entry> binder = new Binder<>(Entry.class);
            binder.forField(fieldTitle)
                    .asRequired()
                    .bind(Entry::getTitle, Entry::setTitle);

            binder.bind(fieldColor, Entry::getColor, Entry::setColor);
            binder.bind(fieldDescription, Entry::getDescription, Entry::setDescription);
            binder.setBean(entry);

            HorizontalLayout buttons = new HorizontalLayout();
            Button buttonSave;
            if (newInstance) {
                buttonSave = new Button("Create", e -> {
                    if (binder.validate().isOk()) {
                        calendar.addEntry(entry);
                    }
                });
            } else {
                buttonSave = new Button("Save", e -> {
                    if (binder.validate().isOk()) {
                        calendar.updateEntry(entry);
                    }
                });
            }
            buttonSave.addClickListener(e -> close());
            buttons.add(buttonSave);

            Button buttonCancel = new Button("Cancel", e -> close());
            buttonCancel.getElement().getThemeList().add("tertiary");
            buttons.add(buttonCancel);

            if (!newInstance) {
                Button buttonRemove = new Button("Remove", e -> {
                    calendar.removeEntry(entry);
                    close();
                });
                ThemeList themeList = buttonRemove.getElement().getThemeList();
                themeList.add("error");
                themeList.add("tertiary");
                buttons.add(buttonRemove);
            }

            add(layout, buttons);
        }
    }
}

package despresso.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import despresso.presenter.ObserverInterface;

import java.util.ArrayList;
import java.util.List;

public class TipsViewImpl extends VerticalLayout implements SubjectInterface {


    private List<ObserverInterface> listeners = new ArrayList<>();

    public TipsViewImpl() {
//        HorizontalLayout line1 = new HorizontalLayout();
//        Label label1 = new Label("Tips label 1");
//        line1.add(label1);
//        HorizontalLayout line2 = new HorizontalLayout();
//        Label label2 = new Label("Tips label 2");
//        line2.add(label2);
//        this.add(line1);
//        this.add(line2);


        // Create the accordion
        Accordion accordion = new Accordion();

        // Create the first tab, specify caption when adding
        AccordionPanel accordionPanel1 = new AccordionPanel();
        accordionPanel1.setContent(new Text("setContent for accordionPanel1"));
        accordion.add(accordionPanel1);

        // Create the second tab, specify caption when adding
        AccordionPanel accordionPanel2 = new AccordionPanel();
        accordionPanel2.setContent(new Text("setContent for accordionPanel2"));
        accordion.add(accordionPanel2);




    }

    /* Elements
     *  Main Tip-Seite:
     *  Button/Leiste zum runterziehen: "Filter setzen"
     *  Liste mit tips
     *  Button: Tip hinzufügen
     *  Meldung, falls gefilterte Liste keine Tipps enthält
     *  Filter-Setzen-Seite: --> Ev. mit Accordion
     * Mood-Auswahl: Kreis mit Sektoren zur Auswahl
     * Ort: Dreiwertiger Button/Schieber: Zu Hause, draussen, Arbeitsplatz
     * Typ: Zweiwertiger Button/Schieber: Körper, Geist
     * Zeit: Dreiwertiger Button/Schieber: Kurz, Mittel, Lang
     * Clear-Button: Alle Filter löschen
     * OK-Button: Filter auf Liste anwenden

     *
     * */


    @Override
    public void removeObserver(ObserverInterface observer) {
        listeners.remove(observer);
    }

    @Override
    public void addObserver(ObserverInterface observer) {
        listeners.add(observer);

    }

}

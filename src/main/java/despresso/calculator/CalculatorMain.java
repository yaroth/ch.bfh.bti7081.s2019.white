package despresso.calculator;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import despresso.logic.MainModel;
import despresso.presenter.MainPresenter;
import despresso.view.MainViewImpl;

/**
 * The main view contains several buttons and  listeners.
 */
// TODO: to run calculator uncomment commented line and comment same line in DespressoMain
//@Route("calculator.html")
@PWA(name = "Project Base for Vaadin Flow", shortName = "Project Base")
public class CalculatorMain extends VerticalLayout {
    public CalculatorMain() {
        // Create the model and the Vaadin view implementation
        CalculatorModel model = new CalculatorModel();
        CalculatorViewImpl view = new CalculatorViewImpl();
        // The presenter connects the model and view
        new CalculatorPresenter(model, view);
        // The view implementation is a Vaadin component
        add(view);
    }

}

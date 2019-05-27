package despresso.logic;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class MainModel {


    public void doSomething() {
        System.out.println("MainModel.doSomething()!");
    }
}

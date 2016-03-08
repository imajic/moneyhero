package moneyhero.ui.views;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


@SpringView(name = NewTransactionView.VIEW_NAME)
public class NewTransactionView extends VerticalLayout implements View{
	public static final String VIEW_NAME = "newTransactionView";
	
	@PostConstruct
	void init() {
	    addComponent(new Label("This is the default view"));
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	    // the view is constructed in the init() method()
	}
}


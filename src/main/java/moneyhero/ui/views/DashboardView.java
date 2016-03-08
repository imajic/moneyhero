package moneyhero.ui.views;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


@SpringView(name = DashboardView.VIEW_NAME)
public class DashboardView extends VerticalLayout implements View{
	public static final String VIEW_NAME = "";
	
	@PostConstruct
	void init() {
	    addComponent(new Label("This is the dashboard view"));
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	    // the view is constructed in the init() method()
	}
}


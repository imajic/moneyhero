package moneyhero.ui.views;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

import java.util.List;
import moneyhero.entity.Account;
import moneyhero.repository.AccountRepository;


@SpringView(name = NewTransactionView.VIEW_NAME)
public class NewTransactionView extends VerticalLayout implements View{
	public static final String VIEW_NAME = "newTransactionView";
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostConstruct
	void init() {
	    addComponent(new Label("This is the default view"));
	    
	    Tree tree = new Tree("Accounts", accountsContainer());
	    tree.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		tree.setItemCaptionPropertyId("name");
	   
	    addComponent(tree);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	    // the view is constructed in the init() method()
	}
	
	private Container accountsContainer(){
		HierarchicalContainer result = new HierarchicalContainer();
		result.addContainerProperty("name", String.class, "");
		
		List<Account> ancestors = new ArrayList<>();
		for (Account account : accountRepository.findAllByOrderByDesignationAsc()){
			Item item = result.addItem(account.getUuid());
			item.getItemProperty("name").setValue(account.getDescription());
			
			Account ancestor = null;
			for (int i = ancestors.size() - 1; i > 0; i--){
				if ( ! isAncestor(ancestors.get(i).getDesignation(), account.getDesignation())){
					ancestors.remove(i);
				}
				else {
					ancestor = ancestors.get(i);
					break;
				}
			}
			
			result.setParent(account.getUuid(), ancestor == null ? null : ancestor.getUuid());
			ancestors.add(account);
		}
		return result;
	}
	
	private boolean isAncestor(long designation, long toDesignation){
		// ORed digits 
		return (designation | toDesignation) == toDesignation;
	}
}


package moneyhero.ui.views;

import javax.annotation.PostConstruct;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.*;
import moneyhero.entity.Currency;
import moneyhero.entity.Transaction;
import moneyhero.repository.TransactionRepository;
import moneyhero.ui.conversion.LongToMoneyConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;

import java.util.Arrays;
import java.util.Date;

import moneyhero.entity.Account;
import moneyhero.repository.AccountRepository;


@SpringView(name = NewTransactionView.VIEW_NAME)
public class NewTransactionView extends FormLayout implements View{
	public static final String VIEW_NAME = "newTransactionView";
	
	@Autowired
	private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

	private DateField date;
	private TextArea description;
	private MoneyField money;
	private ComboBox debits;
	private ComboBox credits;

	private Button saveButton;

	public NewTransactionView() {

	}

	@PostConstruct
	void init() {

		date = new DateField("Date");
		description = new TextArea("Description");
        description.setWidth(100, Unit.PERCENTAGE);
		money = new MoneyField("Amount");

		debits = new ComboBox("Debits", accountsContainer());
        debits.setItemCaptionMode(ItemCaptionMode.PROPERTY);
        debits.setItemCaptionPropertyId("name");
        debits.setWidth(100, Unit.PERCENTAGE);
        debits.setFilteringMode(FilteringMode.CONTAINS);
        debits.setNewItemsAllowed(false);
        debits.setInvalidAllowed(false);
        debits.setNullSelectionAllowed(false);

		credits = new ComboBox("Credits", accountsContainer());
        credits.setWidth(100, Unit.PERCENTAGE);
        credits.setItemCaptionMode(ItemCaptionMode.PROPERTY);
        credits.setItemCaptionPropertyId("name");
        credits.setFilteringMode(FilteringMode.CONTAINS);
        credits.setNewItemsAllowed(false);
        credits.setInvalidAllowed(false);
        credits.setNullSelectionAllowed(false);

		saveButton = new Button("Save", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Transaction transaction = new Transaction(date.getValue() == null ? null : new java.sql.Date(date.getValue().getTime())
                    , (String) description.getValue()
                    , (Currency) money.currency.getValue()
                    , (Long) money.amount.getConvertedValue()
                    , (Account) debits.getValue()
                    , (Account) credits.getValue());
                try {
                    transactionRepository.save(transaction);
                    showNotification(Notification.Type.TRAY_NOTIFICATION
                            , "Transaction creation"
                            , "Transaction has been successfully created");
                    resetToDefaults();
                }
                catch (Throwable t){
                    showNotification(Notification.Type.ERROR_MESSAGE
                            , "Transaction creation failed"
                            , t.getMessage());
                }
            }
        });

		addComponent(date);
	    addComponent(description);
		addComponent(money);
	    addComponent(debits);
		addComponent(credits);

		addComponent(saveButton);

        resetToDefaults();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	    // the view is constructed in the init() method()
	}

    private void resetToDefaults(){
        date.setValue(new Date());

    }

	private class MoneyField extends CustomComponent{
		private final TextField amount;
		private final ComboBox currency;

		public MoneyField() {
			this(null);
		}
		public MoneyField(String caption) {
			setCaption(caption);

			amount = new TextField();
            amount.setConverter(new LongToMoneyConverter());
			currency = new ComboBox(null, new IndexedContainer(Arrays.asList(Currency.values())));

			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			layout.addComponent(amount);
			layout.addComponent(currency);

			setCompositionRoot(layout);

			setSizeFull();
		}
	}

    private IndexedContainer accountsContainer(){
        IndexedContainer result = new IndexedContainer();
        result.addContainerProperty("name", String.class, "");

        for (Account account : accountRepository.findAllByOrderByDesignationAsc()){
            Item item = result.addItem(account);
            item.getItemProperty("name").setValue(account.getDescription());

        }
        return result;
    }

    private void showNotification(Notification.Type type, String caption, String content){
        Notification notification = new Notification(caption, content, type);
        notification.show(Page.getCurrent());
    }
}


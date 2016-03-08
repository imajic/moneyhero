package moneyhero.entity;

import java.io.Serializable;
import java.util.UUID;

public class AccountAmountId implements Serializable {
	
	private Currency currency;
	private Account account;

	public AccountAmountId() {
	}

	public AccountAmountId(Account account, Currency currency) {
		this.account = account;
		this.currency = currency;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Account getAccount() {
		return account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountAmountId other = (AccountAmountId) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (currency != other.currency)
			return false;
		return true;
	}

	

}
package moneyhero.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="account_amount")
@IdClass(AccountAmountId.class)
public class AccountAmount {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "uuid", referencedColumnName = "uuid")
	private Account account;
	
	@Id
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(name = "currency")
	private Currency currency;
	
	@Basic
	@Column(name = "amount")
	private Long amount;
	
	@Column(name = "version")
    @Version
    private Integer version;
	

    protected AccountAmount() {}

    public AccountAmount(Account account, Currency currency) {
        this.account = account;
        this.currency = currency;
        this.amount = 0L;
    }

	public Account getAccount() {
		return account;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Long getAmount() {
		return amount;
	}

	public Integer getVersion() {
		return version;
	}

    
}

package moneyhero.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="account_amount")
public class AccountAmount {
	
	@EmbeddedId
	private AccountAmountId id;
	
	@Basic
	@Column(name = "amount")
	private Long amount;
	
	@Column(name = "version")
    @Version
    private Integer version;
	

    protected AccountAmount() {}

    public AccountAmount(Account account, Currency currency) {
        this.id = new AccountAmountId(account, currency);
        this.amount = 0L;
    }

	public AccountAmountId getId() {
		return id;
	}

	public Long getAmount() {
		return amount;
	}

	public Integer getVersion() {
		return version;
	}

    
}

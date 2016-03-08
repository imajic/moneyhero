package moneyhero.entity;

import java.sql.Date;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
    private UUID uuid;
	
    @Basic
    @Column(name = "transaction_timestamp")
    private Date transactionTimestamp;
	
	@Basic
	@Column(name = "description")
	private String description;
	
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(name = "currency")
	private Currency currency;
	
	@Basic
	@Column(name = "amount")
	private Long amount;
	
	@ManyToOne
	@JoinColumn(name = "credits", referencedColumnName = "uuid")
	private Account credits;
	
	@ManyToOne
	@JoinColumn(name = "debits", referencedColumnName = "uuid")
	private Account debits;
    
	@Column(name = "version")
    @Version
    private Integer version;

    protected Transaction() {}

    public Transaction(Date transactionTimestamp, String description, Currency currency, Long amount, Account credits, Account debits) {
        this.uuid = UUID.randomUUID();
        this.transactionTimestamp = transactionTimestamp == null ? new Date(new java.util.Date().getTime()) : transactionTimestamp;
        this.description = description;
        this.currency = currency;
        this.amount = amount;
        this.credits = credits;
        this.debits = debits;
    }

	public UUID getUuid() {
		return uuid;
	}

	public Date getTransactionTimestamp() {
		return transactionTimestamp;
	}

	public String getDescription() {
		return description;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Long getAmount() {
		return amount;
	}

	public Account getCredits() {
		return credits;
	}

	public Account getDebits() {
		return debits;
	}

	public Integer getVersion() {
		return version;
	}
}

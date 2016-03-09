package moneyhero.entity;

import java.util.Map;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name="account")
public class Account {
	
	@Id
    private UUID uuid;
    
	@Basic
	@Column(name = "designation")
	@Min(value = 1000000L)
	@Max(value = 9999999L)
	private Long designation;
    
	@Basic
	@Column(name = "description")
	private String description;
	
	@Column(name = "version")
    @Version
    private Integer version;
	
	@OneToMany(mappedBy = "id.account", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@MapKey(name="id.currency")
	private Map<Currency, AccountAmount> amounts;

    protected Account() {}

    public Account(Long designation, String description) {
        this.uuid = UUID.randomUUID();
        this.designation = designation;
        this.description = description;
    }

    
	public UUID getUuid() {
		return uuid;
	}

	public Long getDesignation() {
		return designation;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		Account other = (Account) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	private static long[] powers10 = new long[]{1L,10L,100L,1000L,10000L,100000L,1000000L};
	public boolean isAncestor(Account toAccount){
		long diff = toAccount.designation - this.designation;
		int diffLength = Long.toString(diff).length();
		return designation % powers10[diffLength] == 0;
	}

	public boolean isDescendant(Account fromAccount){
		return fromAccount.isAncestor(this);
	}

	public int getLevel(){
		for (int i = powers10.length - 1; i > 0; i--){
			if (this.getDesignation() % powers10[i] == 0){
				return powers10.length - i + 1;
			}
		}
		return powers10.length;
	}
}

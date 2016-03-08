package moneyhero.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import moneyhero.entity.Account;

public interface AccountRepository extends CrudRepository<Account, UUID>{
	
}

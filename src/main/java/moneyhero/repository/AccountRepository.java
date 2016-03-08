package moneyhero.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import moneyhero.entity.Account;

public interface AccountRepository extends Repository<Account, UUID>{
	List<Account> findAllByOrderByDesignationAsc();
}

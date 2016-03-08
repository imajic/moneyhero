package moneyhero.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import moneyhero.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, UUID>{
	
}

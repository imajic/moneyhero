ALTER TABLE account_amount 
  ADD version INTEGER NOT NULL
    AFTER amount;
    
ALTER TABLE transaction 
  ADD version INTEGER NOT NULL
    AFTER debits;
CREATE TABLE account(
  uuid BINARY(16) NOT NULL,
  designation NUMERIC(7) NOT NULL,
  description VARCHAR(255),
  version INTEGER NOT NULL,
  PRIMARY KEY (uuid)
);

CREATE TABLE account_amount(
  uuid BINARY(16) NOT NULL,
  currency CHAR(3) NOT NULL,
  amount NUMERIC(20) NOT NULL,
  PRIMARY KEY (uuid, currency),
  FOREIGN KEY uuid_fk_account(uuid) REFERENCES account(uuid)
);

CREATE TABLE transaction(
  uuid BINARY(16) NOT NULL,
  transaction_timestamp DATETIME NOT NULL,
  description VARCHAR(255),
  currency CHAR(3) NOT NULL,
  amount NUMERIC(20) NOT NULL,
  credits BINARY(16) NOT NULL,
  debits BINARY(16) NOT NULL,
  PRIMARY KEY (uuid),
  FOREIGN KEY credits_fk_account(credits) REFERENCES account(uuid),
  FOREIGN KEY debits_fk_account(debits) REFERENCES account(uuid),
  INDEX transaction_timestamp_index(transaction_timestamp)
);


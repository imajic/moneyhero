CREATE TABLE external_entry(
  uuid BINARY(16) NOT NULL,
  entry_type VARCHAR(10) NOT NULL,
  external_source VARCHAR(10) NOT NULL,
  external_source_id VARCHAR(20),
  created_by VARCHAR(20) NOT NULL,
  creation_timestamp DATETIME NOT NULL,
  last_modified_by VARCHAR(20) NOT NULL,
  last_modification_timestamp DATETIME NOT NULL,
  version INTEGER NOT NULL,
  PRIMARY KEY (uuid),
  UNIQUE INDEX external_id_index(entry_type, external_source, external_source_id)
);

CREATE TABLE external_entry_underyling(
  uuid BINARY(16) NOT NULL,
  issuer BINARY(16) NOT NULL,
  PRIMARY KEY (uuid),
  FOREIGN KEY uuid_fk_external_entry_uuid(uuid) REFERENCES external_entry(uuid),
  FOREIGN KEY issuer_fk_external_entry(issuer) REFERENCES external_entry(uuid)
);

CREATE TABLE external_entry_underlying_exchange(
  uuid BINARY(16) NOT NULL,
  underlying BINARY(16) NOT NULL,
  exchange BINARY(16) NOT NULL,
  PRIMARY KEY (uuid),
  FOREIGN KEY uuid_fk_external_entry_uuid(uuid) REFERENCES external_entry(uuid),
  FOREIGN KEY underyling_fk_external_entry_underlying(underyling) REFERENCES external_entry(uuid),
  FOREIGN KEY exchange_fk_external_entry_exchange(exchange) REFERENCES external_entry(uuid)
);

CREATE TABLE external_entry_history (
  uuid BINARY(16) NOT NULL,
  external_entry BINARY(16) NOT NULL,
  entry_type VARCHAR(10) NOT NULL,
  valid_from DATETIME NOT NULL,
  valid_to DATETIME DEFAULT NULL,
  created_by VARCHAR(20) NOT NULL,
  creation_timestamp DATETIME NOT NULL,
  last_time_seen DATETIME NOT NULL,
  version INT NOT NULL,
  PRIMARY KEY (uuid),
  INDEX external_entry_history_chrono_index(external_entry, valid_from),
  FOREIGN KEY external_entry_history_fk_external_entry(external_entry) REFERENCES external_entry(uuid)
);

CREATE TABLE external_entry_history_issuer (
  external_entry_history BINARY(16) NOT NULL,
  status CHAR(1) NOT NULL,
  issuer_name VARCHAR(70) NOT NULL,
  issuer_country CHAR(2),
  raw_data TEXT,
  PRIMARY KEY (external_entry_history),
  FOREIGN KEY external_entry_history_issuer_fk_external_entry_history(external_entry_history) REFERENCES external_entry_history(uuid)
);

CREATE TABLE external_entry_history_equity (
  external_entry_history BINARY(16) NOT NULL,
  status CHAR(1) NOT NULL,
  isin CHAR(12) NULL,
  cusip CHAR(9) NULL,
  shares_outstanding NUMERIC(12,5) NULL,
  primary_exchange_code CHAR(6) NULL,
  currency_code CHAR(3) NULL,
  pair_value NUMERIC(14,5) NULL,
  no_pair_value BOOLEAN NULL,
  raw_data TEXT,
  PRIMARY KEY (external_entry_history),
  FOREIGN KEY external_entry_history_equity_fk_external_entry_history(external_entry_history) REFERENCES external_entry_history(uuid)
);

CREATE TABLE external_entry_history_exchange (
  external_entry_history BINARY(16) NOT NULL,
  status CHAR(1) NOT NULL,
  exchange_name VARCHAR(70) NULL,
  mic CHAR(4) NULL,
  country_code CHAR(2) NULL,
  raw_data TEXT,
  PRIMARY KEY (external_entry_history),
  FOREIGN KEY external_entry_history_exchange_fk_external_entry_history(external_entry_history) REFERENCES external_entry_history(uuid)
);

CREATE TABLE external_entry_history_underlying_exchange (
  external_entry_history BINARY(16) NOT NULL,
  status CHAR(1) NULL,
  list_status CHAR(1) NULL,
  local_code VARCHAR(50) NULL,
  min_trading_quantity NUMBER(11) NULL,
  list_date DATE NULL,
  raw_data TEXT,
  PRIMARY KEY (external_entry_history),
  FOREIGN KEY external_entry_history_fk_external_entry_history(external_entry_history) REFERENCES external_entry_history(uuid)
);

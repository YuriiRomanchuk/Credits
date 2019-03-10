create table if not exists banks
(
  id                 SERIAL       NOT NULL,
  name               varchar(250) NOT NULL,
  registrationNumber int          NOT NULL,
  PRIMARY KEY (id)
);
create table if not exists credit_lines
(
  id                 SERIAL       NOT NULL,
  bank_id            int          NOT NULL,
  percent            int          NOT NULL,
  earlyRedemption    boolean,
  increaseCreditLine boolean,
  maxSum             int          NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT credit_lines_bank_id_fkey FOREIGN KEY (bank_id)
    REFERENCES banks (id)
);
create table if not exists clients
(
  id                 SERIAL       NOT NULL,
  name               varchar(250) NOT NULL,
  PRIMARY KEY (id)
);

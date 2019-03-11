create table if not exists banks
(
  id                 SERIAL       NOT NULL,
  name               varchar(250) NOT NULL,
  registrationNumber int          NOT NULL,
  PRIMARY KEY (id)
);
create table if not exists credit_lines
(
  id                 SERIAL NOT NULL,
  bank_id            int    NOT NULL,
  percent            int    NOT NULL,
  earlyRedemption    boolean,
  increaseCreditLine boolean,
  maxSum             int    NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT credit_lines_bank_id_fkey FOREIGN KEY (bank_id)
    REFERENCES banks (id)
);
create table if not exists clients
(
  id   SERIAL       NOT NULL,
  name varchar(250) NOT NULL,
  PRIMARY KEY (id)
);
create table if not exists client_credits
(
  id              SERIAL NOT NULL,
  client_id         int    NOT NULL,
  credit_line_id int    NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT client_credits_client_id_fkey FOREIGN KEY (client_id)
    REFERENCES clients (id),
  CONSTRAINT client_credits_credit_line_id_fkey FOREIGN KEY (credit_line_id)
    REFERENCES credit_lines (id)
);

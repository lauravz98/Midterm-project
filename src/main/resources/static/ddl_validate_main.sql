DROP SCHEMA IF EXISTS midterm_project;
CREATE SCHEMA midterm_project;
USE midterm_project;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  password VARCHAR(255),
  user_role_enum VARCHAR(255),
  username VARCHAR(255),
  PRIMARY KEY (id));
  
INSERT INTO user (name, password, user_role_enum, username)
VALUES
  ("Vanna Maynard",	"$2a$10$s1f6Ja1Em0waIDjWVitydO5Ndm4qghXlV96fi67sa8gCIpcQuey0u", "ACCOUNT_HOLDER",	"user1"), -- aa
  ("Ruth Hogan",	"$2a$10$s1f6Ja1Em0waIDjWVitydO5Ndm4qghXlV96fi67sa8gCIpcQuey0u", "ACCOUNT_HOLDER",	"Gage"), -- aa
  ("Erasmus Crane",	"$2a$10$0UjAe5iUgivZNMNLKP06Tep7NTZi5hev0kBY4lBFYGYQSxuyelakm", "ACCOUNT_HOLDER",	"Anjolie"), -- bb
  ("Mona Sanford",	"$2a$10$0UjAe5iUgivZNMNLKP06Tep7NTZi5hev0kBY4lBFYGYQSxuyelakm", "ACCOUNT_HOLDER",	"Dylan"), -- bb
  ("Galena Brock",	"$2a$10$0UjAe5iUgivZNMNLKP06Tep7NTZi5hev0kBY4lBFYGYQSxuyelakm", "ACCOUNT_HOLDER",	"Minerva"), -- bb
  ("Stacy Maddox",	"$2a$10$0UjAe5iUgivZNMNLKP06Tep7NTZi5hev0kBY4lBFYGYQSxuyelakm", "ACCOUNT_HOLDER",	"Maecenas"), -- bb
  ("Avye Morris",	"$2a$10$dlTs7Sf1EqOP4zF51WYduO.UrR/dm5rX6qUCs/wVJlhfIWhng1K9S", "ADMIN",			"admin1"), -- 123
  ("Rhea Patton",	"$2a$10$ERPQPq/RG6eS9z2lGhE.NeQg7Z0VdO/akegT5mhg02Th3z.fT2MQ6", "ADMIN",			"Jamalia"); -- 1234
--  SELECT * FROM user;

DROP TABLE IF EXISTS account_holder;
CREATE TABLE account_holder (
  city VARCHAR(255),
  country VARCHAR(255),
  number_address INT,
  street_name VARCHAR(255),
  date_of_birth DATETIME,
  mailing_address INT,
  id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES user(id));
  
INSERT INTO account_holder (city, country, number_address, street_name, date_of_birth, mailing_address, id)
VALUES
  ("Cáceres",	"France",	3, "Enim. Avenue", 			"1979-08-13", "57660", 1),
  ("Melilla",	"Spain",	4, "Pellentesque, Avenue",  "1981-11-29", "86745", 2),
  ("Alacant",	"France",	5, "Iaculis Ave",			"1951-11-11", "41881", 3),
  ("Limoges",	"Spain",	3, "Euismod Road",			"2002-04-13", "34870", 4),
  ("Boulogne",	"Spain",	8, "P.O. Box Road",			"1970-09-27", "20717", 5),
  ("Vierzon",	"Spain",	8, "Quam. St.",				"1976-08-17", "77683", 6);
-- SELECT * FROM account_holder;
  
  DROP TABLE IF EXISTS admin;
  CREATE TABLE admin (
  id BIGINT NOT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user(id));

INSERT INTO admin(id) VALUES (7), (8);
-- SELECT * FROM admin;

CREATE TABLE account (
  account_id BIGINT NOT NULL AUTO_INCREMENT,
  amount_penalty_fee DECIMAL(19,2),
  currency_penalty_fee VARCHAR(255),
  amount_balance DECIMAL(19,2),
  currency_balance VARCHAR(255),
  creation_date DATETIME(6),
  secret_key VARCHAR(255),
  status_account VARCHAR(255),
  type_account VARCHAR(255),
  last_consult DATE,
  id_primary_owner BIGINT NOT NULL,
  id_secondary_owner BIGINT,
  PRIMARY KEY (account_id),
    FOREIGN KEY (id_secondary_owner)  REFERENCES account_holder(id),
    FOREIGN KEY (id_primary_owner) REFERENCES account_holder(id));
    
INSERT INTO account (amount_penalty_fee, currency_penalty_fee, amount_balance, currency_balance, creation_date,
secret_key, status_account, type_account, last_consult, id_primary_owner, id_secondary_owner)
VALUES
  (40, "USD", 1010.15, 	"USD", "2013-01-26", "YNQ23PAD6PT", "ACTIVE", "CREDIT_CARD",		"2015-01-26", 1, 4),
  (40, "USD", 9926.26, 	"USD", "2012-03-10", "HPJ73PDN1LX", "ACTIVE", "SAVINGS", 		 	"2016-01-26", 2, NULL),
  (40, "USD", 10005.17,	"USD", "2012-05-07", "VEO46CPC5DR", "ACTIVE", "CHECKING", 		 	"2017-01-26", 3, 2),
  (40, "USD", 5988.95, 	"USD", "2012-06-06", "IHK24EXJ0UC", "ACTIVE", "STUDENT_CHECKING", 	"2018-01-26", 4, 3),
  (40, "USD", 3928.51, 	"USD", "2013-04-03", "HJI31DWR1EF", "ACTIVE", "CREDIT_CARD",		"2015-01-26", 5, NULL),
  (40, "USD", 19038.03, "USD", "2011-07-20", "DDU41YKE3VR", "ACTIVE", "SAVINGS",			"2016-01-26", 6, NULL),
  (40,"USD", 7002.27, 	"USD", "2011-10-19", "AIH83QOW7IX", "ACTIVE", "CHECKING",			"2017-01-26", 1, 2),
  (40,"USD", 2081.89, 	"USD", "2012-09-19", "HNQ39OBE2BE", "ACTIVE", "STUDENT_CHECKING",	"2018-01-26", 3, 4),
  (40,"USD", 10007.76, 	"USD", "2012-06-28", "DNV32ZPK1VE", "ACTIVE", "CREDIT_CARD",		"2019-01-26", 4, NULL),
  (40,"USD", 19025.52, 	"USD", "2011-11-24", "MIL79CSO1KC", "ACTIVE", "SAVINGS",			"2015-01-26", 5, NULL),
  (40,"USD", 9937.97, 	"USD", "2012-10-12", "VON85OHM1LC", "ACTIVE", "CHECKING",			"2016-01-26", 5, NULL);
  -- SELECT * FROM account;

DROP TABLE IF EXISTS saving;
CREATE TABLE saving (
  amount_minimum_balance_max DECIMAL(19,2),
  currency_minimum_balance_max VARCHAR(255),
  amount_minimum_balance_min DECIMAL(19,2),
  currency_minimum_balance_min VARCHAR(255),
  amount_minimum_balance DECIMAL(19,2),
  currency_minimum_balance VARCHAR(255),
  max_interest_rate DECIMAL(19,4),
  min_interest_rate DECIMAL(19,4),
  interest_rate DECIMAL(19,4),
  id BIGINT NOT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES account(account_id));
    
INSERT INTO saving(amount_minimum_balance_max, currency_minimum_balance_max, amount_minimum_balance_min, currency_minimum_balance_min, amount_minimum_balance, currency_minimum_balance, max_interest_rate, min_interest_rate, interest_rate, id)
VALUES(1000,"USD", 100,"USD",100,"USD",0.5, 0.0025, 0.01, 2),
	(1000,"USD", 100,"USD", 200, "USD",0.5, 0.0025, 0.2, 6),
	(1000,"USD", 100, "USD", 300, "USD",0.5, 0.0025, 0.4, 10);
-- SELECT * FROM saving;

CREATE TABLE checking (
  amount_minimum_balance DECIMAL(19,2),
  currency_minimum_balance VARCHAR(255),
  amount_monthly_maintenance_fee DECIMAL(19,2),
  currency_monthly_maintenance_fee VARCHAR(255),
  id BIGINT NOT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES account(account_id));
INSERT INTO checking(amount_minimum_balance, currency_minimum_balance, amount_monthly_maintenance_fee, currency_monthly_maintenance_fee, id)
VALUES(250,"USD", 12,"USD", 3),
	(250,"USD", 12,"USD", 7),
	(250,"USD", 12,"USD", 11);
-- SELECT * FROM checking;

DROP TABLE IF EXISTS credit_card;
CREATE TABLE credit_card (
  amount_credit_limit_max DECIMAL(19,2),
  currency_credit_limit_max VARCHAR(255),
  amount_credit_limit_min DECIMAL(19,2),
  currency_credit_limit_min VARCHAR(255),
  amount_credit_limit DECIMAL(19,2),
  currency_credit_limit VARCHAR(255),
  max_interest_rate DECIMAL(19,4),
  min_interest_rate DECIMAL(19,4),
  interest_rate DECIMAL(19,4),
  id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES account(account_id));
    
INSERT INTO credit_card(amount_credit_limit_max, currency_credit_limit_max, amount_credit_limit_min, currency_credit_limit_min, amount_credit_limit, 
	currency_credit_limit, max_interest_rate, min_interest_rate, interest_rate, id)
VALUES(100000,"USD", 100,"USD",1234,"USD",0.2, 0.1, 0.1, 1),
	(100000,"USD", 100,"USD", 2340, "USD",0.2, 0.1, 0.12, 5),
	(100000,"USD", 100, "USD", 3400, "USD",0.2, 0.1, 0.14, 9);
-- SELECT * FROM credit_card;

CREATE TABLE student_checking (
  id BIGINT NOT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES account(account_id));
INSERT INTO student_checking(id) 
VALUES(4), (8);
-- SELECT * FROM student_checking;
    
DROP TABLE IF EXISTS third_party;
CREATE TABLE third_party (
  id BIGINT NOT NULL AUTO_INCREMENT,
  hash_key VARCHAR(255),
  PRIMARY KEY (id));
INSERT INTO third_party(hash_key)
VALUES("647086f4-b159-4c4b-91fb-b785bb1364f8"), -- random UUID
("c6671408-ae94-4628-9b75-9a83ee936acd") ;
SELECT * FROM third_party;
  
DROP TABLE IF EXISTS transfer;
CREATE TABLE transfer (
  id BIGINT NOT NULL AUTO_INCREMENT,
  amount DECIMAL(19,2),
  currency VARCHAR(255),
  time_transfer DATETIME(6),
  date_transfer DATE,
  PRIMARY KEY (id));
SELECT * FROM transfer;

INSERT INTO transfer(amount, currency, time_transfer, date_transfer)
VALUES(10000,"USD", "2022-01-03 09:59:31.69", "2022-01-03"),
	(20000,"USD", "2022-01-03 09:59:31.69", "2022-01-03"),
	(5000,"USD", "2022-04-03 09:59:31.69", "2022-04-03");

SELECT date_transfer, SUM(amount) AS total_day FROM transfer GROUP BY date_transfer;

SELECT MAX(sum_per_day.total_day) FROM (SELECT date_transfer, SUM(amount) AS total_day FROM transfer GROUP BY date_transfer) AS sum_per_day;
-- SELECT * FROM (SELECT SUM(amount) FROM transfer GROUP BY time_transfer;)
-- WHERE time_transfer;

-- SELECT AVG(count_table.id_column) FROM (SELECT a.id, COUNT(o.id) AS id_column FROM opportunity o
-- JOIN account a ON a.id = o.account_id
-- GROUP BY a.id) AS count_table;

DROP TABLE IF EXISTS transfer_own;
CREATE TABLE transfer_own (
  id BIGINT NOT NULL,
  account_sender_id BIGINT,
  name_sender VARCHAR(255),
  account_receiver_id BIGINT,
  name_receiver VARCHAR(255),
  PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES transfer(id),
	FOREIGN KEY (account_sender_id)  REFERENCES account(account_id),
    FOREIGN KEY (account_receiver_id) REFERENCES account(account_id));
SELECT * FROM transfer_own;

DROP TABLE IF EXISTS transfer_third_party_send;
CREATE TABLE transfer_third_party_send (
  id BIGINT NOT NULL,
  hash_key VARCHAR(255),
  account_receiver_id BIGINT,
  name_receiver VARCHAR(255),
  secret_key VARCHAR(255),
  PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES transfer(id),
    FOREIGN KEY (account_receiver_id) REFERENCES account(account_id));
SELECT * FROM transfer_third_party_send;

DROP TABLE IF EXISTS transfer_third_party_receive;
CREATE TABLE transfer_third_party_receive (
  id BIGINT NOT NULL,
  account_sender_id BIGINT,
  name_sender VARCHAR(255),
  hash_key VARCHAR(255),
  PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES transfer(id),
	FOREIGN KEY (account_sender_id)  REFERENCES account(account_id));
SELECT * FROM transfer_third_party_receive;
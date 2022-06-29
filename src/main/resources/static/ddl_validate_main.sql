DROP SCHEMA IF EXISTS midterm_project;
CREATE SCHEMA midterm_project;
USE midterm_project;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  password VARCHAR(255),
  user_role VARCHAR(255),
  username VARCHAR(255),
  PRIMARY KEY (id));
  
INSERT INTO user (name, password, user_role, username)
VALUES
  ("Vanna Maynard","DMH98HOI7SM","ACCOUNT_HOLDER","Rigel"),
  ("Ruth Hogan","TNK15PLY8QN","ACCOUNT_HOLDER","Gage"),
  ("Erasmus Crane","HJW82PMC3NP","ACCOUNT_HOLDER","Anjolie"),
  ("Mona Sanford","VFM81JRW6VN","ACCOUNT_HOLDER","Dylan"),
  ("Galena Brock","FXQ68FQD8NH","ACCOUNT_HOLDER","Minerva"),
  ("Stacy Maddox","OJU66CHK9KW","ACCOUNT_HOLDER","Maecenas"),
  ("Avye Morris","VJE78BMG6TW","ADMIN","Clayton"),
  ("Rhea Patton","NTW33UCW2NY","ADMIN","Jamalia");  
  SELECT * FROM user;

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
  
INSERT INTO account_holder (city,country,number_address,street_name,date_of_birth,mailing_address,id)
VALUES
  ("Cáceres","France",3,"Ap #164-9094 Enim. Avenue","1979-08-13","57660",1),
  ("Melilla","Spain",4,"5587 Pellentesque, Avenue","1981-11-29","86745",2),
  ("Alacant","France",5,"Ap #564-1999 Iaculis Ave","1951-11-11","41881",3),
  ("Limoges","Spain",3,"291-9929 Euismod Road","2002-04-13","34870",4),
  ("Boulogne-Billancourt","Spain",8,"P.O. Box 143, 6258 Eu Road","1970-09-27","20717",5),
  ("Vierzon","Spain",8,"Ap #124-7118 Quam. St.","1976-08-17","77683",6);
   SELECT * FROM account_holder;
  
  DROP TABLE IF EXISTS admin;
  CREATE TABLE admin (
  id BIGINT NOT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user(id));

INSERT INTO admin(id) VALUES (7), (8);
SELECT * FROM admin;

CREATE TABLE account (
  account_id BIGINT NOT NULL AUTO_INCREMENT,
  amount_penalty_fee DECIMAL(19,2),
  currency_penalty_fee VARCHAR(255),
  amount_balance DECIMAL(19,2),
  currency_balance VARCHAR(255),
  creation_date DATETIME(6),
  now DATETIME(6),
  secret_key VARCHAR(255),
  status_account VARCHAR(255),
  type_account VARCHAR(255),
  id_primary_owner BIGINT NOT NULL,
  id_secondary_owner BIGINT,
  PRIMARY KEY (account_id),
    FOREIGN KEY (id_secondary_owner)  REFERENCES account_holder(id),
    FOREIGN KEY (id_primary_owner) REFERENCES account_holder(id));
    
INSERT INTO account (amount_penalty_fee,currency_penalty_fee,amount_balance,currency_balance,creation_date,now,secret_key,status_account,type_account,id_primary_owner,id_secondary_owner)
VALUES
  (40,"USD",1010.15,"USD","2023-01-26","2022-06-29","YNQ23PAD6PT","ACTIVE","CREDIT",1,4),
  (40,"USD",9926.26,"USD","2022-03-10","2022-06-29","HPJ73PDN1LX","ACTIVE","SAVINGS",2,NULL),
  (40,"USD",10005.17,"USD","2022-05-07","2022-06-29","VEO46CPC5DR","ACTIVE","CHECKING",3,2),
  (40,"USD",5988.95,"USD","2022-06-06","2022-06-29","IHK24EXJ0UC","ACTIVE","STUDENT_SAVINGS",4,3),
  (40,"USD",3928.51,"USD","2023-04-03","2022-06-29","HJI31DWR1EF","ACTIVE","CREDIT",5,NULL),
  (40,"USD",19038.03,"USD","2021-07-20","2022-06-29","DDU41YKE3VR","ACTIVE","SAVINGS",6,NULL),
  (40,"USD",7002.27,"USD","2021-10-19","2022-06-29","AIH83QOW7IX","ACTIVE","CHECKING",1,2),
  (40,"USD",2081.89,"USD","2022-09-19","2022-06-29","HNQ39OBE2BE","ACTIVE","STUDENT_SAVINGS",3,4),
  (40,"USD",10007.76,"USD","2022-06-28","2022-06-29","DNV32ZPK1VE","ACTIVE","CREDIT",4,NULL),
  (40,"USD",19025.52,"USD","2021-11-24","2022-06-29","MIL79CSO1KC","ACTIVE","SAVINGS",5,NULL),
  (40,"USD",9937.97,"USD","2022-10-12","2022-06-29","VON85OHM1LC","ACTIVE","CHECKING",5,NULL);
SELECT * FROM account;

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
SELECT * FROM saving;

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
SELECT * FROM checking;

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
    
INSERT INTO credit_card(amount_credit_limit_max, currency_credit_limit_max, amount_credit_limit_min, currency_credit_limit_min, amount_credit_limit, currency_credit_limit, max_interest_rate, min_interest_rate, interest_rate, id)
VALUES(100000,"USD", 100,"USD",1234,"USD",0.2, 0.1, 0.1, 1),
	(100000,"USD", 100,"USD", 2340, "USD",0.2, 0.1, 0.12, 5),
	(100000,"USD", 100, "USD", 3400, "USD",0.2, 0.1, 0.14, 9);
SELECT * FROM credit_card;

CREATE TABLE student_checking (
  id BIGINT NOT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES account(account_id));
INSERT INTO student_checking(id) 
VALUES(4), (8);
SELECT * FROM student_checking;
    

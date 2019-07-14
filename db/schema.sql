DROP TABLE IF EXISTS ac;
DROP TABLE IF EXISTS ta;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS accounts;
create TABLE accounts(
    acctNo serial ,
    nameOfacct TEXT,
    balance FLOAT,
    PRIMARY KEY(acctNo)
    );
CREATE TABLE customer(
    userName TEXT ,
    password TEXT,
    fname TEXT,
    lname TEXT,
    PRIMARY KEY (userName)
);
CREATE TABLE transactions(
    id SERIAL,
    ammount FLOAT,
    kindofTrans TEXT,
    timetable TIMESTAMP,
    PRIMARY KEY (id)
);
CREATE TABLE ac(
    ID int, 
    usrname TEXT,
    acctNum SERIAL, 
    PRIMARY KEY (ID),
    FOREIGN KEY (usrname) REFERENCES customer(userName) ON DELETE CASCADE,
    FOREIGN KEY (acctNum) REFERENCES accounts(acctNo) ON DELETE CASCADE

);
CREATE TABLE ta(
    accNo SERIAL,
    transid SERIAL,
    PRIMARY KEY(accNo, transid),
    FOREIGN KEY (accNo) REFERENCES accounts(acctNo) ON DELETE CASCADE,
    FOREIGN KEY (transid) REFERENCES transactions(id) ON DELETE CASCADE

);

INSERT INTO accounts(nameOfacct,balance) VALUES('Savings', 0.0);
SELECT * FROM accounts;
SELECT * FROM transactions;
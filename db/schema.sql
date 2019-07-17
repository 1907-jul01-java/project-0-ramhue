DROP TABLE IF EXISTS ac;
DROP TABLE IF EXISTS ta;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS accounts;
create TABLE accounts(
    acctNo SERIAL PRIMARY KEY,
    nameOfacct TEXT,
    balance FLOAT
    );
CREATE TABLE customer(
    customerid SERIAL PRIMARY KEY,
    userName TEXT UNIQUE,
    password TEXT,
    fname  TEXT,
    lname TEXT
);
CREATE TABLE transactions(
    tracid SERIAL,
    ammount FLOAT,
    kindofTrans TEXT,
    timetable TIMESTAMP,
    PRIMARY KEY (tracid)
);
CREATE TABLE ac(
    ID SERIAL, 
    acctNum INT, 
    custid INT,
    PRIMARY KEY (ID),
    FOREIGN KEY (custid) REFERENCES customer(customerid) ON DELETE CASCADE,
    FOREIGN KEY (acctNum) REFERENCES accounts(acctNo) ON DELETE CASCADE

);
CREATE TABLE ta(
    taID SERIAL,
    transid SERIAL,
    accNo INT,
    PRIMARY KEY(taID),
    FOREIGN KEY (accNo) REFERENCES accounts(acctNo) ON DELETE CASCADE,
    FOREIGN KEY (transid) REFERENCES transactions(tracid) ON DELETE CASCADE

);

/*INSERT INTO accounts(nameOfacct,balance) VALUES('Savings', 0.0);
SELECT * FROM accounts;
SELECT * FROM transactions;*/
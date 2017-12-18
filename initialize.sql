-- Run this file after createuser.sql

CREATE TABLE EMPLOYEE (
    EmployeeId NUMBER NOT NULL UNIQUE,
    FirstName VARCHAR2(20),
    LastName VARCHAR2(20),
    Username VARCHAR2(20) NOT NULL UNIQUE,
    Pass VARCHAR2(200) NOT NULL,
    SuperId NUMBER,
    IsManager NUMBER(1) DEFAULT 0,
    CONSTRAINT PK_EmployeeId PRIMARY KEY (EmployeeId),
    CONSTRAINT FK_SuperId FOREIGN KEY (SuperId) REFERENCES Employee (EmployeeId),
    CONSTRAINT BOOL_ISMANAGER CHECK (IsManager >= 0)
);

CREATE TABLE REIMBURSEMENT (
    ReimbursementId NUMBER NOT NULL,
    EmployeeId NUMBER NOT NULL,
    HandlerId NUMBER NOT NULL,
    SubmissionDate DATE DEFAULT SYSDATE,
    RequestDate DATE DEFAULT SYSDATE,
    Description VARCHAR(50),
    Amount NUMBER DEFAULT 0,
    Approved NUMBER(1) DEFAULT 0,
    CONSTRAINT PK_ReimbursementId PRIMARY KEY (ReimbursementId),
    CONSTRAINT FK_EmployeeId FOREIGN KEY (EmployeeId) REFERENCES Employee (EmployeeId),
    CONSTRAINT FK_HandlerId FOREIGN KEY (HandlerId) REFERENCES Employee (EmployeeId),
    CONSTRAINT VALID_Amount CHECK (Amount > 0),
    CONSTRAINT BOOL_Approved CHECK (Approved >= 0)
);

CREATE OR REPLACE TRIGGER TR_EMPLOYEE_INSERT
BEFORE INSERT ON EMPLOYEE
FOR EACH ROW
DECLARE
    encryptedRaw RAW(200);
    encryptionType PLS_INTEGER := 
        DBMS_CRYPTO.ENCRYPT_AES256 +
        DBMS_CRYPTO.CHAIN_CBC +
        DBMS_CRYPTO.PAD_PKCS5;
BEGIN
    encryptedRaw := DBMS_CRYPTO.ENCRYPT(
        src => UTL_I18N.STRING_TO_RAW (:NEW.Pass, 'AL32UTF8'),
        typ => encryptionType,
        key => DBMS_CRYPTO.RANDOMBYTES (32)
    );
    DBMS_OUTPUT.PUT_LINE ('Encrypted String: ' || encryptedRaw);
    :NEW.Pass := utl_raw.cast_to_varchar2(encryptedRaw);
    
    SELECT SQ_EMPLOYEEID.NEXTVAL
    INTO :NEW.EmployeeId FROM DUAL;
END;
/

DROP SEQUENCE SQ_EMPLOYEEID;

CREATE SEQUENCE SQ_EMPLOYEEID
START WITH 1
INCREMENT BY 1;

INSERT INTO EMPLOYEE(FirstName, LastName, Username, Pass, SuperId)
    VALUES ('Doggy', 'McDogFace', 'doge2018','#notmydog', null);

INSERT INTO EMPLOYEE(FirstName, LastName, Username, Pass, SuperId)
    VALUES ('Ronald', 'McDonald', 'mickeyds', '#notmydog', null);
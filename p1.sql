CREATE SCHEMA ers;
SET search_path = my_schema, "$user", ers;

CREATE TABLE ERS_REIMBURSEMENT(
    reimb_id SERIAL,
    reimb_amount INTEGER,
    reimb_submitted TIMESTAMP,
    reimb_resolved TIMESTAMP,
    reimb_description VARCHAR(250),
    reimb_receipt TEXT,
    reimb_author INTEGER,
    reimb_resolver INTEGER,
    reimb_status_id INTEGER,
    reimb_type_id INTEGER,
    CONSTRAINT ERS_REIMBURSMENT_PK PRIMARY KEY (reimb_id)
);

CREATE TABLE ERS_REIMBURSEMENT_STATUS(
    reimb_status_id SERIAL,
    reimb_status VARCHAR(10),
    CONSTRAINT REIMB_STATUS_PK PRIMARY KEY (reimb_status_id)
);

CREATE TABLE ERS_REIMBURSEMENT_TYPE(
    reimb_type_id SERIAL,
    reimb_type VARCHAR(10),
    CONSTRAINT REIMB_TYPE_PK PRIMARY KEY (reimb_type_id)
);

CREATE TABLE ERS_USER_ROLES(
    ers_user_role_id SERIAL,
    user_role VARCHAR(10),
    CONSTRAINT ERS_USER_ROLES_PK PRIMARY KEY (ers_user_role_id)
);

CREATE TABLE ERS_USERS(
    ers_users_id SERIAL,
    ers_username VARCHAR(50) UNIQUE,
    ers_password VARCHAR(50),
    user_first_name VARCHAR(100),
    user_last_name VARCHAR(100),
    user_email VARCHAR(150) UNIQUE,
    user_role_id INTEGER,
    CONSTRAINT ERS_USERS_PK PRIMARY KEY(ers_users_id)
);


alter table ERS_REIMBURSEMENT
add constraint ERS_REIMBURSEMENT_STATUS_FK
foreign key (reimb_status_id)
references ERS_REIMBURSEMENT_STATUS (reimb_status_id)
on delete cascade;

alter table ERS_REIMBURSEMENT
add constraint ERS_REIMBURSEMENT_TYPE_FK
foreign key (reimb_type_id)
references ERS_REIMBURSEMENT_TYPE (reimb_type_id)
on delete cascade;

alter table ERS_REIMBURSEMENT
add constraint ERS_USERS_FK_AUTH
foreign key (reimb_author)
references ERS_USERS (ers_users_id)
on delete cascade;

alter table ERS_REIMBURSEMENT
add constraint ERS_USERS_FK_RSLVR
foreign key (reimb_resolver)
references ERS_USERS (ers_users_id)
on delete cascade;

alter table ERS_USERS
add constraint USER_ROLES_FK
foreign key (user_role_id)
references ERS_USER_ROLES (ers_user_role_id)
on delete cascade;

INSERT INTO ERS_USER_ROLES (user_role) VALUES
('employee');

INSERT INTO ERS_USER_ROLES (user_role) VALUES
('manager');

INSERT INTO ERS_REIMBURSEMENT_STATUS (reimb_status) VALUES
('Approved');

INSERT INTO ERS_REIMBURSEMENT_STATUS (reimb_status) VALUES
('Pending');

INSERT INTO ERS_REIMBURSEMENT_STATUS (reimb_status) VALUES
('Denied');

INSERT INTO ERS_REIMBURSEMENT_TYPE (reimb_type) VALUES
('Travel');

INSERT INTO ERS_REIMBURSEMENT_TYPE (reimb_type) VALUES
('Client');

INSERT INTO ERS_REIMBURSEMENT_TYPE (reimb_type) VALUES
('Corporate');

INSERT INTO ERS_REIMBURSEMENT_TYPE (reimb_type) VALUES
('Personal');

INSERT INTO ERS_USERS (ers_username, ers_password, 
user_first_name, user_last_name, user_email, user_role_id) VALUES
('man1', '1234', 'Jack', 'Sparrow', 'jsparrow@corp.com',2);

INSERT INTO ERS_USERS (ers_username, ers_password, 
user_first_name, user_last_name, user_email, user_role_id) VALUES
('emp1', '4321', 'Will', 'Turner', 'wturner@corp.com',1);
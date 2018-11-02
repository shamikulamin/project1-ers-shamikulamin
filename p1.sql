CREATE TABLE ERS_REIMBURSEMENT(
    reimb_id INTEGER NOT NULL AUTO_INCREMENT,
    reimb_amount INTEGER,
    reimb_submitted TIMESTAMP,
    reimb_resolved TIMESTAMP,
    reimb_description VARCHAR(250),
    reimb_receipt TEXT,
    reimb_author INTEGER,
    reimb_resolver INTEGER,
    reimb_status_id INTEGER,
    reimb_type_id INTEGER,
    PRIMARY KEY (reimb_id)
);

CREATE TABLE ERS_REIMBURSEMENT_STATUS(
    reimb_status_id INTEGER NOT NULL AUTO_INCREMENT,
    reimb_status VARCHAR(10),
    PRIMARY KEY (reimb_status_id)
);

CREATE TABLE ERS_RE
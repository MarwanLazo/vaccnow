DROP TABLE IF EXISTS tbl_branch;
create table tbl_branch (
    branch_id int NOT NULL AUTO_INCREMENT,
    create_by varchar(255),
    create_date timestamp,
    modified_by varchar(255),
    modified_date timestamp,
    branch_email varchar(255),
    branch_location varchar(255),
    branch_name varchar(255),
    branch_phone varchar(255),
    branch_work_end_date timestamp,
    branch_work_start_date timestamp,
    primary key (branch_id)
);
DROP TABLE IF EXISTS tbl_schedule_vaccination;
create table tbl_schedule_vaccination (
    schedule_vac_id int NOT NULL AUTO_INCREMENT,
    create_by varchar(255),
    create_date timestamp,
    modified_by varchar(255),
    modified_date timestamp,
    schedule_vac_confirm char(255),
    schd_vac_request_email varchar(255) NOT NULL,
    schedule_vac_payment_method integer,
    schedule_vac_request varchar(255),
    schedule_vac_desc varchar(255),
    schedule_vac_time timestamp,
    primary key (schedule_vac_id),
    CONSTRAINTschd_vac_request_email_UNIQUE UNIQUE (schd_vac_request_email)
);
DROP TABLE IF EXISTS tbl_vaccine;
create table if not exists tbl_vaccine (
    vaccine_id int NOT NULL AUTO_INCREMENT,
    create_by varchar(255),
    create_date timestamp,
    modified_by varchar(255),
    modified_date timestamp,
    vaccine_country varchar(255),
    vaccine_manufacture varchar(255),
    vaccine_name varchar(255),
    vaccine_notes varchar(255),
    primary key (vaccine_id)
);
DROP TABLE IF EXISTS tbl_vaccine_branch;
CREATE TABLE IF NOT EXISTS tbl_vaccine_branch (
    create_by VARCHAR(255),
    create_date TIMESTAMP,
    modified_by VARCHAR(255),
    modified_date TIMESTAMP,
    vaccine_id INTEGER NOT NULL,
    branch_id INTEGER NOT NULL,
    PRIMARY KEY (branch_id, vaccine_id),
    INDEX FK_tbl_vaccine_idx (vaccine_id ASC),
    INDEX FK_tbl_branch_idx (branch_id ASC),
    FOREIGN KEY (vaccine_id) REFERENCES tbl_vaccine (vaccine_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (branch_id) REFERENCES tbl_branch (branch_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
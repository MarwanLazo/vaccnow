CREATE DATABASE IF NOT EXISTS temp_db_;
USE temp_db_;
-- 
CREATE TABLE IF NOT EXISTS tbl_branch (
    branch_id int NOT NULL AUTO_INCREMENT,
    branch_email varchar(255),
    branch_location varchar(255),
    branch_name varchar(255),
    branch_phone varchar(255),
    branch_work_end_date timestamp,
    branch_work_start_date timestamp,
    create_by varchar(255),
    create_date timestamp,
    modified_by varchar(255),
    modified_date timestamp,
    primary key (branch_id)
) ENGINE = InnoDB;
-- 
CREATE TABLE IF NOT EXISTS tbl_schedule_vaccination (
    schedule_vac_id int NOT NULL AUTO_INCREMENT,
    schedule_vac_confirm char(255),
    schd_vac_request_email varchar(255) NOT NULL UNIQUE,
    schedule_vac_payment_method integer,
    schedule_vac_request varchar(255),
    schedule_vac_desc varchar(255),
    schd_vac_branch_id int,
    schedule_vac_time timestamp,
    create_by varchar(255),
    create_date timestamp,
    modified_by varchar(255),
    modified_date timestamp,
    primary key (schedule_vac_id),
    FOREIGN KEY (branch_id) REFERENCES tbl_branch (branch_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB;
-- 
-- ALTER TABLE tbl_schedule_vaccination
-- ADD UNIQUE INDEX `schd_vac_request_email_UNIQUE` (`schd_vac_request_email` ASC);
-- ALTER TABLE tbl_schedule_vaccination
-- ADD CONSTRAINT UC_schd_vac_request_email UNIQUE (schd_vac_request_email);
CREATE TABLE IF NOT EXISTS tbl_vaccine (
    vaccine_id int NOT NULL AUTO_INCREMENT,
    vaccine_manufacture varchar(255),
    vaccine_name varchar(255),
    vaccine_notes varchar(255),
    vaccine_country varchar(255),
    create_by varchar(255),
    create_date timestamp,
    modified_by varchar(255),
    modified_date timestamp,
    primary key (vaccine_id)
) ENGINE = InnoDB;
-- 
CREATE TABLE IF NOT EXISTS tbl_vaccine_branch (
    vaccine_id INTEGER NOT NULL,
    branch_id INTEGER NOT NULL,
    create_by VARCHAR(255),
    create_date TIMESTAMP,
    modified_by VARCHAR(255),
    modified_date TIMESTAMP,
    PRIMARY KEY (branch_id, vaccine_id),
    INDEX FK_tbl_vaccine_idx (vaccine_id ASC),
    INDEX FK_tbl_branch_idx (branch_id ASC),
    FOREIGN KEY (vaccine_id) REFERENCES tbl_vaccine (vaccine_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (branch_id) REFERENCES tbl_branch (branch_id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB;
-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user, availability, doctor, appointment, prescription, review, patient;


CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32) NOT NULL,
  salt varchar(255) NOT NULL
);

CREATE TABLE availability (
    availability_id SERIAL PRIMARY KEY,
    doctor_id Integer,
    starting_time time NOT NULL,
    ending_time time NOT NULL
);

CREATE TABLE doctor (
    doctor_id Integer PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    address varchar(50) NOT NULL,
    phone_number varchar(20) NOT NULL,
    medical_specialty varchar(50) NOT NULL,
    hour_cost NUMERIC(10,2) NOT NULL
);

CREATE TABLE appointment (
    appointment_id SERIAL PRIMARY KEY,
    starting_time time NOT NULL,
    ending_time time NOT NULL,
    day_of_week varchar(30) NOT NULL,
    patient_id Integer,
    doctor_id Integer,
    confirmed boolean DEFAULT 'true'
);

CREATE TABLE prescription (
    prescription_id SERIAL PRIMARY KEY,
    patient_id Integer,
    doctor_id Integer,
    prescription_name varchar(20) NOT NULL,
    cost NUMERIC(10,2) NOT NULL
);

CREATE TABLE review (
    review_id SERIAL PRIMARY KEY,
    doctor_id Integer,
    patient_id Integer,
    title varchar(50) NOT NULL,
    description varchar(100) NOT NULL,
    rating Integer NOT NULL,
    answer varchar(100)
);


CREATE TABLE patient (
    patient_id Integer PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    age Integer NOT NULL,
    gender varchar(50) NOT NULL,
    email varchar(50) NOT NULL
);


ALTER TABLE patient  -- child table
ADD CONSTRAINT fk_patient_app_user --
FOREIGN KEY (patient_id) -- element de la child table (fk)
references app_user(id); -- mother table (PK)

ALTER TABLE doctor
ADD CONSTRAINT fk_doctor_app_user
FOREIGN KEY (doctor_id)
references app_user(id);

ALTER TABLE appointment
ADD CONSTRAINT fk_appointment_doctor
FOREIGN KEY (doctor_id)
references doctor(doctor_id);

ALTER TABLE appointment
ADD CONSTRAINT fk_appointment_patient
FOREIGN KEY (patient_id)
references patient(patient_id);

ALTER TABLE prescription
ADD CONSTRAINT fk_prescription_doctor
FOREIGN KEY (doctor_id)
references doctor(doctor_id);

ALTER TABLE prescription
ADD CONSTRAINT fk_prescription_patient
FOREIGN KEY (patient_id)
references patient(patient_id);

ALTER TABLE review
ADD CONSTRAINT fk_review_doctor
FOREIGN KEY (doctor_id)
references doctor(doctor_id);

ALTER TABLE review
ADD CONSTRAINT fk_review_patient
FOREIGN KEY (patient_id)
references patient(patient_id);

ALTER TABLE availability
ADD CONSTRAINT fk_availability_doctor
FOREIGN KEY (doctor_id)
references doctor(doctor_id);

COMMIT;
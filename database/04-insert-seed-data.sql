-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

INSERT INTO app_user(user_name, password, role, salt)
VALUES ('patient1', 'Password1', 'patient', '4141414141')
     , ('doctor1', 'Password1', 'doctor', '41448325')
     , ('doctor2', 'Password1', 'doctor', '414145321')
--      no relacionar
     , ('doctor3', 'Password1', 'doctor', '414145321')
     , ('patient2', 'Password1', 'patient', '414145321')
--      no relacionar
     , ('doctor4', 'Password1', 'doctor', '414145321')
     , ('doctor5', 'Password1', 'doctor', '414145321')
     , ('doctor6', 'Password1', 'doctor', '414145321')
     , ('doctor7', 'Password1', 'doctor', '414145321')
     , ('doctor8', 'Password1', 'doctor', '414145321')
     , ('doctor9', 'Password1', 'doctor', '414145321')
     , ('doctor10', 'Password1', 'doctor', '414145321')
     , ('doctor11', 'Password1', 'doctor', '414145321')
    , ('doctor12', 'Password1', 'doctor', '414145321')
    , ('doctor13', 'Password1', 'doctor', '414145321')
    , ('doctor14', 'Password1', 'doctor', '414145321')
    , ('doctor15', 'Password1', 'doctor', '414145321')
     , ('doctor16', 'Password1', 'doctor', '414145321')
    , ('doctor17', 'Password1', 'doctor', '414145321')
    , ('patient3', 'Password1', 'patient', '4141414141')
     , ('patient4', 'Password1', 'patient', '4141414141')
     , ('patient5', 'Password1', 'patient', '4141414141')
     , ('patient6', 'Password1', 'patient', '4141414141');


INSERT INTO doctor(doctor_id, first_name, last_name, email, address, phone_number, medical_specialty, hour_cost)
VALUES ( (SELECT id FROM app_user WHERE user_name = 'doctor1'), 'Meredith', 'Grey', 'meredithGrey@gmail.com', '974 Grasselli Street', '202-380-8016', 'Gastroenterology', '200')
      , ( (SELECT id FROM app_user WHERE user_name = 'doctor2'), 'Derek', 'Shephard', 'derekShep@gmail.com', '2644 Rhode Island Avenue', '713-483-7537', 'Neurology', '190')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor4'), 'Cristina', 'Yung', 'cyoung@gmail.com', '1537 Monroe Street', '715-465-1045', 'Cardiology', '185')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor5'), 'Miranda', 'Bailey', 'mirandaB@gmail.com', '465 Black Stallion Road', '949-495-3686', 'Cardiology', '180')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor6'), 'Mark', 'Sloan', 'markSloan@gmail.com', '79 Irving Road', '603-415-3571', 'Cardiology', '196')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor7'), 'Jackson', 'Avery', 'drAvery@gmail.com', '1173 Alexander Avenue', '505-245-2394', 'Gynecology', '210')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor8'), 'Owen', 'Hunt', 'drAvery@gmail.com', '2971 Peck Court', '603-415-3571', 'Gynecology', '215')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor9'), 'Izzie', 'Stevens', 'drAvery@gmail.com', '2639 Dane Street', '864-342-6698', 'Endocrinology', '220')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor10'), 'April', 'Kepner', 'drAvery@gmail.com', '656 Prudence Street', '949-495-3686', 'Psychiatry', '170')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor11'), 'Jo', 'Wilson', 'jowilson@gmail.com', '1258 Edgewood Road', '870-953-1963', 'Sports Medicine', '130')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor12'), 'Richard', 'Webber', 'richardwebber@gmail.com', '211 Parkway Drive', '520-371-3301', 'Neurology', '150')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor13'), 'Andrew', 'DeLuca', 'andrewdeluca@gmail.com', '1819 Whitman Court', '203-551-3258', 'Ophthalmology', '190')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor14'), 'Nick', 'Marsh', 'nickmarsh@gmail.com', '2171 Carson Street', '859-271-2511', 'Anesthesiology', '130')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor15'), 'Addison', 'Forbes', 'addisonforbes@gmail.com', '2446 George Avenue', '251-269-5648', 'Psychiatry', '140')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor16'), 'Arizona', 'Robbins', 'arizonarobbins@gmail.com', '1807 Pheasant Ridge Road', '215-502-3449', 'Pediatrician', '200')
     , ( (SELECT id FROM app_user WHERE user_name = 'doctor17'), 'Maggie', 'Pierce', 'maggiepierce@gmail.com', '2868 Rainbow Road', '626-263-4835', 'Geriatric Medicine', '110');

INSERT INTO patient(patient_id, first_name, last_name, age, gender, email)
VALUES ( (SELECT id FROM app_user WHERE user_name = 'patient1'), 'Magali', 'Carreyra', '23', 'Female', 'maga@gmail.com')
     , ( (SELECT id FROM app_user WHERE user_name = 'patient3'), 'Regina', 'Mcfadden', '23', 'Female', 'reginamacfadden@gmail.com')
     , ( (SELECT id FROM app_user WHERE user_name = 'patient4'), 'Rojin', 'Fritz', '55', 'Male', 'rojinfri@gmail.com')
     , ( (SELECT id FROM app_user WHERE user_name = 'patient5'), 'Cally', 'Grainger', '29', 'Other', 'callygrainger@gmail.com')
     , ( (SELECT id FROM app_user WHERE user_name = 'patient6'), 'Leja', 'Horton', '33', 'Female', 'lejahorton@gmail.com');

INSERT INTO availability(doctor_id, starting_time, ending_time)
VALUES(2, '09:00', '17:00')
      ,(3, '10:00', '18:00')
     ,(6, '10:00', '18:00')
     ,(7, '10:00', '18:00')
     ,(8, '10:00', '18:00')
     ,(9, '10:00', '18:00')
     ,(10, '10:00', '18:00')
     ,(11, '10:00', '18:00')
     ,(12, '10:00', '18:00')
     ,(13, '10:00', '18:00')
     ,(14, '10:00', '18:00')
     ,(15, '10:00', '18:00')
     ,(16, '10:00', '18:00')
     ,(17, '10:00', '18:00')
     ,(18, '10:00', '18:00')
     ,(19, '10:00', '18:00');

INSERT INTO appointment(starting_time, ending_time, day_of_week, patient_id, doctor_id)
VALUES ('09:00', '10:00', 'Monday', 1, 2);

INSERT INTO review(doctor_id, patient_id, title, description, rating)
VALUES (6, 1, 'Best doctor ever!', 'So awesome and not at all expensive. I would recommend.', 4)
    , (6, 20, 'Excellent service', 'Good Health care provider!', 5)
     , (7, 1, 'Delays on the appointments', 'Im always waiting hours to see this doctor', 2)
     , (7, 20, 'Not so good', 'Got me waiting for my appointment for a long time', 3)
     , (6, 21, 'Amazing Professional', 'Solved my problem efficienly! I will come back soon! ', 4)
     , (7, 21, 'Bad personal odor', 'Dr had questionable hygiene ', 2)
     ,  (2, 1, 'Best doctor ever!', 'So awesome and not at all expensive. I would recommend.', 4);


COMMIT;
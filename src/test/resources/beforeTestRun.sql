INSERT INTO LANGUAGE (name) VALUES ('Java');
INSERT INTO TEACHER (firstName, lastName) VALUES ('Jan', 'Smith');
INSERT INTO STUDENT (firstName, lastName, LANGUAGE_ID, TEACHER_ID) VALUES ('Adrian', 'Kowalski', 1, 1);
INSERT INTO LESSON (date, STUDENT_ID, TEACHER_ID) VALUES ('2026-12-07 10:00:00', 1, 1);

-- INSERT INTO LANGUAGE (name, version) VALUES ('Java', 1);
--INSERT INTO TEACHER (first_name, last_name, version) VALUES ('Jan', 'Smith', 1);
--INSERT INTO STUDENT (first_name, last_name, language_id, teacher_id, version) VALUES ('Adrian', 'Kowalski', 1, 1, 1);
--INSERT INTO LESSON (date, student_id, teacher_id, version) VALUES ('2026-12-07 10:00:00', 1, 1, 1);


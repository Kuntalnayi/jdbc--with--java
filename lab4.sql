DELIMITER //
CREATE PROCEDURE getStudentEmail(IN stud_id INT, OUT stud_email VARCHAR(100))
BEGIN
    SELECT email INTO stud_email FROM student WHERE id = stud_id;
END //
DELIMITER ;

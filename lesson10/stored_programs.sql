DROP TRIGGER CheckPharmacyToDeleteStreet;

-- 1.1
-- -------------------------------------------------------------------------------
DELIMITER //
CREATE TRIGGER CheckEmployeeToDeletePost
BEFORE DELETE
ON post FOR EACH ROW
BEGIN
	IF(SELECT COUNT(*) FROM employee WHERE post = old.post) > 0
	THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot delete Post because some Employee use it.";
	END IF;
END //
DELIMITER

DELIMITER //
CREATE TRIGGER CheckPostToInsertEmployee
BEFORE INSERT
ON employee FOR EACH ROW
BEGIN 
	IF(SELECT COUNT(*) FROM post WHERE post = new.post) = 0
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot set this Post because it's absent in DB.";
    END IF;
END  //
DELIMITER

DELIMITER //
CREATE TRIGGER CheckPharmacyToInsertEmployee
BEFORE INSERT
ON employee FOR EACH ROW
BEGIN
	IF(SELECT COUNT(*) FROM pharmacy WHERE pharmacy.id = new.pharmacy_id) = 0
	THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot set this Pharmacy because it's absent in DB.";
	END IF;
END //
DELIMITER

DELIMITER //
CREATE TRIGGER CheckEmployeeToDeletePharmacy
BEFORE DELETE
ON pharmacy FOR EACH ROW
BEGIN
	IF(SELECT COUNT(*) FROM employee WHERE pharmacy_id = old.id) > 0
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot delete Pharmacy because some Employee use it.";
	END IF;
END //
DELIMITER

DELIMITER //
CREATE TRIGGER CheckStreetToInsertPharmacy
BEFORE INSERT
ON pharmacy FOR EACH ROW
BEGIN
	IF(SELECT COUNT(*) FROM street WHERE street.street = new.street) = 0
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot set this Street because it's absent in DB.";
	END IF;
END //
DELIMITER 

DELIMITER //
CREATE TRIGGER CheckPharmacyToDeleteStreet
BEFORE DELETE
ON street FOR EACH ROW
BEGIN
	IF(SELECT COUNT(*) FROM pharmacy WHERE pharmacy.street = old.street) > 0
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot delete Street because some Pharmacy use it.";
	END IF;
END //
DELIMITER

DELIMITER //
CREATE TRIGGER CheckPharmacyToInsertPharmacyMedicine
BEFORE INSERT
ON pharmacy_medicine FOR EACH ROW
BEGIN 
	IF(SELECT COUNT(*) FROM pharmacy WHERE id = new.street_id) = 0
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot join this Pharmacy because it's absent in DB.";
	END IF;
END //
DELIMITER

DELIMITER //
CREATE TRIGGER CheckMedicineToInsertPharmacyMedicine
BEFORE INSERT
ON pharmacy_medicine FOR EACH ROW
BEGIN
	IF(SELECT COUNT(*) FROM medicine WHERE id = new.medicine_id) = 0
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot join this Medicine because it's absent in DB.";
	END IF;
END //
DELIMITER 

DELIMITER //
CREATE TRIGGER CheckPharmacyMedicineToDeletePharmacy
BEFORE DELETE
ON pharmacy FOR EACH ROW
BEGIN
	IF(SELECT COUNT(*) FROM pharmacy_medicine WHERE pharmacy_id = old.id) > 0
	THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot delete Pharmacy because some Medicine use it.";
	END IF;
END //

DELIMITER //
CREATE TRIGGER CheckPharmacyMedicineToDeleteMedicine
BEFORE DELETE
ON medicine FOR EACH ROW
BEGIN
	IF(SELECT COUNT(*) FROM pharmacy_medicine WHERE medicine_id = old.id) > 0
	THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot delete Medicine because some Pharmacy use it.";
	END IF;
END //
DELIMITER

DELIMITER //
CREATE TRIGGER CheckMedicineToInsertMedicineZone
BEFORE INSERT
ON medicine_zone FOR EACH ROW
BEGIN
	IF(SELECT COUNT(*) FROM medicine WHERE id = new.medicine_id) = 0
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot join this Medicine because it's absent in DB.";
	END IF;
END //
DELIMITER

DELIMITER //
CREATE TRIGGER CheckZoneToInsertMedicineZone
BEFORE INSERT
ON medicine_zone FOR EACH ROW
BEGIN
	IF(SELECT COUNT(*) FROM zone WHERE id = new.zone_id) = 0
	THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot join this Zone because it's absent in DB.";
	END IF;
END //
DELIMITER

DELIMITER //
CREATE TRIGGER CheckMedicineZoneToDeleteMedicine
BEFORE DELETE
ON medicine FOR EACH ROW
BEGIN
	IF(SELECT COUNT(*) FROM medicine_zone WHERE medicine_id = old.id) > 0
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot delete Medicine because it's present in join table with Zone";
	END IF;
END //
DELIMITER

DELIMITER //
CREATE TRIGGER CheckMedicineZoneToDeleteZone
BEFORE DELETE
ON zone FOR EACH ROW
BEGIN
	IF(SELECT COUNT(*) FROM medicine_zone WHERE zone_id = old.id) > 0
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Cannot delete Zone because it's present in join table with Medicine";
	END IF;
END //
DELIMITER

-- 1.2
-- -------------------------------------------------------------------------------
DELIMITER //
CREATE TRIGGER NotZeroExperience
BEFORE INSERT
ON employee FOR EACH ROW
BEGIN
	IF(new.identity_number LIKE '%00')
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Identity number can't end with two zeros.";
	END IF;
END //
DELIMITER


-- 1.3
-- -------------------------------------------------------------------------------			
DELIMITER //
CREATE TRIGGER TriggerMinistryCode
AFTER INSERT
ON medicine FOR EACH ROW
BEGIN
	IF NOT(new.ministry_code RLIKE '^[^МП][^МП]-[0-9][0-9][0-9]-[0-9][0-9]$')
THEN
	SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Ministry code isn't correct";
	END IF;
END //
DELIMITER

-- 1.4 
-- -------------------------------------------------------------------------------
DELIMITER //
CREATE TRIGGER NotModificationInPost
BEFORE UPDATE
ON post FOR EACH ROW
BEGIN
	SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "Can't modificate rows of this table";
END //
DELIMITER 


-- 2.1
-- -------------------------------------------------------------------------------
DELIMITER //
CREATE PROCEDURE InsertEmployee
	   (IN p_surname VARCHAR(30), IN p_name CHAR(30), IN p_midle_name VARCHAR(30),
		IN p_identity_number CHAR(10), IN p_passport CHAR(10), IN p_experience DECIMAL(10,1),
		IN p_birthday DATE, IN p_post VARCHAR(15), IN p_pharmacy_id INT)
BEGIN
	INSERT INTO employee
		(surname, name, midle_name, identity_number, passport,
        experience, birthday, post, pharmacy_id)
	VALUES (p_surname, p_name, p_midle_name, p_identity_number, p_passport,
			p_experience, p_birthday, p_post, p_pharmacy_id);
END //
DELIMITER

DELIMITER //
CALL insert_employee('nikita', 'nikiforov', 'middlename', '1236', '9843', 20.7, '2019-12-12', 1, 1);
DELIMITER

-- 2.2
-- -------------------------------------------------------------------------------
DELIMITER //
CREATE PROCEDURE EmployeeDataBases()
BEGIN
	DECLARE done INT DEFAULT false;
    DECLARE temp_name CHAR(30);
    DECLARE temp_surname VARCHAR(30);
    
    DECLARE EmployeeCursor CURSOR 
		FOR SELECT surname, name FROM employee;
	
    DECLARE CONTINUE HANDLER
		FOR NOT FOUND SET done = true;
    OPEN EmployeeCursor;
    
    myLoop: LOOP
		FETCH EmployeeCursor INTO temp_surname, temp_name;
        IF done = true THEN LEAVE myLoop;
        END IF;
        SET @temp_query = CONCAT('CREATE DATABASE ', temp_surname, temp_name);
        PREPARE myquery FROM @temp_query;
        EXECUTE myquery;
        DEALLOCATE PREPARE myquery;
	END LOOP;
	CLOSE EmployeeCursor;
END //

-- 3.1
-- -------------------------------------------------------------------------------
DELIMITER //
CREATE FUNCTION MinEmployee()
				RETURNS decimal(10, 1)
BEGIN
	RETURN(SELECT MIN(experience) FROM employee);
END //

SELECT * FROM employee WHERE experience = MinEmployee();

-- 3.2
-- -------------------------------------------------------------------------------
DELIMITER //
CREATE FUNCTION NameHouseNumber(pharmacy_id INT)
	RETURNS VARCHAR(25)
BEGIN
	RETURN (SELECT CONCAT(name, ' ', building_number) AS Result
			FROM pharmacy WHERE id = pharmacy_id);
END //

SELECT *, NameHouseNumber(pharmacy_id) AS new FROM employee;
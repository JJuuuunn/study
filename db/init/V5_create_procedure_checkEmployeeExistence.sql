DELIMITER $$

CREATE PROCEDURE CheckEmployeeExistence(
    IN p_ENo VARCHAR(255),
    OUT p_Exists BOOLEAN
)
BEGIN
    SELECT EXISTS(
        SELECT 1 FROM EMPLOYEE WHERE ENo = p_ENo
    ) INTO p_Exists;
END $$

DELIMITER ;
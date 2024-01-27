DELIMITER $$

CREATE PROCEDURE InsertManger(
    IN p_ENo VARCHAR(255) CHARACTER SET UTF8MB4,
    IN p_Name VARCHAR(255) CHARACTER SET UTF8MB4,
    IN p_Year INT,
    IN p_Month INT,
    IN p_Date INT,
    IN p_Role VARCHAR(255) CHARACTER SET UTF8MB4,
    IN p_SecNo VARCHAR(255) CHARACTER SET UTF8MB4,
    OUT p_AckMessage VARCHAR(255) CHARACTER SET UTF8MB4
)

BEGIN
    DECLARE employeeExists INT;

    SELECT COUNT(*) INTO employeeExists FROM EMPLOYEE WHERE ENo = p_ENo;
    IF
        employeeExists > 0 THEN
        SET p_AckMessage = '등록 실패 - 직원이 이미 존재합니다.';
    ELSE
        INSERT INTO EMPLOYEE VALUES(NULL, p_ENo, p_Name, p_Year, p_Month, p_Date, p_Role, p_SecNo);
        SET p_AckMessage = '등록 성공 - 새로운 직원이 추가되었습니다.';

    END IF;

END $$

DELIMITER ;
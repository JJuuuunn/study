DELIMITER $$

CREATE PROCEDURE InsertEmployee(
    IN p_ENo VARCHAR(255) character set utf8,
    IN p_Name VARCHAR(255) character set utf8,
    IN p_Year INT,
    IN p_Month INT,
    IN p_Date INT,
    IN p_Role VARCHAR(255) character set utf8,
    OUT p_AckMessage VARCHAR(255) character set utf8
)

BEGIN
    # 직원이 이미 존재하는지 확인하기 위한 변수
    DECLARE employeeExists BOOLEAN;

    # 직원이 이미 존재하는지 확인
    # V4_create_function_checkEmployeeExistence.sql 참고
    SET employeeExists = CheckEmployeeExistence(p_ENo);


    IF employeeExists > 0 THEN # 직원이 이미 존재하는 경우
        SET p_AckMessage = '등록 실패 - 직원이 이미 존재합니다.';
    ELSE # 직원이 존재하지 않는 경우
        INSERT INTO EMPLOYEE VALUES (null, p_ENo, p_Name, p_Year, p_Month, p_Date, p_Role, null);
        SET p_AckMessage = '등록 성공 - 새로운 직원이 추가되었습니다.';
    END IF;

END $$

DELIMITER ;
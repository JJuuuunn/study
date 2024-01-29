DELIMITER $$

CREATE PROCEDURE findbyIdWithRole(
    IN p_ENo VARCHAR(255) CHARACTER SET utf8,
    OUT p_AckMessage VARCHAR(255) CHARACTER SET utf8,
    OUT p_Role BIGINT
)
BEGIN
    DECLARE eno VARCHAR(255);
    DECLARE access_role VARCHAR(255);
    DECLARE expired_at DATETIME;

    SELECT e.ENO, r.access_role, r.expired_at
    INTO eno, access_role, expired_at
    FROM EMPLOYEE e
             LEFT JOIN RESTRICTION_LEVEL r ON e.id = r.employee_id
    WHERE e.ENo = p_ENo
    ORDER BY r.EXPIRED_AT DESC
    LIMIT 1;

    IF eno is null THEN
        SET p_AckMessage = '직원 조회 실패 - 해당 직원이 존재하지 않습니다.';
    ELSEIF access_role IS NULL THEN
        SET p_AckMessage = '권한 조회 실패 - 해당 직원은 권한이 없습니다.';
    ELSEIF expired_at < NOW() THEN
        SET p_AckMessage = '권한 조회 실패 - 해당 직원의 권한이 만료되었습니다.';
    ELSE
        SET p_Role = access_role;
    END IF;
END $$

DELIMITER ;
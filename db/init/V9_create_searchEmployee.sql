DELIMITER $$

CREATE PROCEDURE searchEmployee(
    IN p_ENo VARCHAR(255) CHARACTER SET utf8,
    IN p_StrEno VARCHAR(255) CHARACTER SET utf8,
    OUT p_AckMessage VARCHAR(255) CHARACTER SET utf8
)
BEGIN
    declare ack_message varchar(255);
    DECLARE access_role bigint;

    call findbyIdWithRole(p_ENo, ack_message, access_role);

    IF CheckEmployeeExistence(p_StrEno) = 0 THEN
        SET p_AckMessage = '조회 실패 - 찾을려는 사원이 존재하지 않습니다.';
    END IF;

    IF ack_message is not null THEN
        SET p_AckMessage = ack_message;
    ELSE
        select e.ENO, e.NAME, e.ENTERYEAR, e.ENTERMONTH, e.ENTERDAY, e.ROLE, e.SECNO
        from EMPLOYEE as e
                 left join RESTRICTION_LEVEL as r
                           on e.ID = r.EMPLOYEE_ID
        where eno = p_StrEno &&
              (r.ACCESS_ROLE <= access_role ||
               r.ACCESS_ROLE is NULL) &&
              e.ROLE != 'Manager';
    END IF;
END $$

DELIMITER ;
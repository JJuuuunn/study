DELIMITER $$

CREATE PROCEDURE getEmployeeList(
    IN p_ENo VARCHAR(255) CHARACTER SET utf8,
    OUT p_AckMessage VARCHAR(255) CHARACTER SET utf8
)
BEGIN
    declare ack_message varchar(255);
    DECLARE access_role bigint;

    call findbyIdWithRole(p_ENo, ack_message, access_role);

    IF ack_message is not null THEN
        SET p_AckMessage = ack_message;
    ELSE
        select e.ENO, e.NAME, e.ENTERYEAR, e.ENTERMONTH, e.ENTERDAY, e.SECNO, e.ROLE
        from EMPLOYEE as e
                 left join RESTRICTION_LEVEL as r
                           on e.ID = r.EMPLOYEE_ID
        where (r.ACCESS_ROLE <= access_role ||
               r.ACCESS_ROLE is NULL) &&
              e.ROLE != 'Manager'
        order by e.eno;

    END IF;
END $$

DELIMITER ;
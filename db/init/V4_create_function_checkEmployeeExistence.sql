DELIMITER $$

CREATE FUNCTION CheckEmployeeExistence(
    p_ENo VARCHAR(255) character set utf8
) RETURNS BOOLEAN

BEGIN
    # 직원번호가 존재하는지 확인하는 함수
    DECLARE exist BOOLEAN;

    # 이미 존재하는 직원이면 TRUE, 존재하지 않는 직원이면 FALSE 를 exist 에 저장
    SELECT EXISTS(SELECT *
                  FROM EMPLOYEE
                  WHERE ENo = p_ENo)
    INTO exist;

    # exist 를 반환
    RETURN exist;
END $$

DELIMITER ;
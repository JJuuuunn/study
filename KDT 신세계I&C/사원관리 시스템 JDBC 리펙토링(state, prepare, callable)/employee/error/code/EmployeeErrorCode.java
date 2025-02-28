package employee.error.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EmployeeErrorCode {
    EMPLOYEE_NOT_FOUND("해당 직원을 찾을 수 없습니다."),
    EMPLOYEE_ALREADY_EXIST("해당 직원은 이미 존재합니다."),
    EMPLOYEE_NOT_INSERTED("직원 정보가 입력되지 않았습니다."),
    EMPLOYEE_NOT_AUTHORIZED("해당 직원은 권한이 없습니다."),
    EMPLOYEE_EXPIRED_AUTHORITY("해당 직원의 권한이 만료되었습니다.");

    private String description;
}

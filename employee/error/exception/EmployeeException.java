package employee.error.exception;

import employee.error.code.EmployeeErrorCode;
import lombok.Getter;

@Getter
public class EmployeeException extends RuntimeException {
    private final EmployeeErrorCode errorCode;
    private final String errorMessage;

    public EmployeeException(EmployeeErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}

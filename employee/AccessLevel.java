package employee;

import lombok.Builder;

import java.time.LocalDateTime;

public class AccessLevel {
    private Long id;
    private String employeeId;
    private RoleLevel roleLevel;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;

    /**
     * 최초 권한을 줄때 사용하기 위한 생성자
     *
     * @param employeeId
     * @param period
     */
    @Builder
    public AccessLevel(String employeeId, Long period) {
        this.employeeId = employeeId;
        this.createdAt = LocalDateTime.now();
        this.expiredAt = LocalDateTime.now().plusDays(period);
    }

    /**
     * DB에서 값을 가져오가나 수정해서 넣을때 사용하기 위한 생성자
     *
     * @param employeeId
     * @param createdAt
     * @param expiredAt
     */
    @Builder
    public AccessLevel(String employeeId, LocalDateTime createdAt, LocalDateTime expiredAt) {
        this.employeeId = employeeId;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }
}

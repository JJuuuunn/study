package employee.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestrictionLevel {
    private Long id;
    @Getter
    private Long employeeId;
    @Getter
    private Long accessRole;
    private LocalDateTime createdAt;
    @Getter
    private LocalDateTime expiredAt;

    /**
     * 최초 권한을 줄때 사용하기 위한 생성자
     *
     * @param employeeId
     * @param period
     */
    @Builder
    public RestrictionLevel(Long employeeId, Long role, Long period) {
        this.employeeId = employeeId;
        this.accessRole = role;
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
    public RestrictionLevel(Long employeeId, Long role, LocalDateTime createdAt, LocalDateTime expiredAt) {
        this.employeeId = employeeId;
        this.accessRole = role;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
    }
}

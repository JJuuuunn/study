package employee.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Getter
@Builder
public class EmployeeWithLevelDto {
    private String eno;
    private String name;
    private Long role;
    private LocalDateTime expiredAt;

    /**
     * ResultSet에서 검색된 데이터를 기반으로 EmployeeWithLevelDto 객체를 생성합니다.
     *
     * @param rs 직원 데이터를 포함하는 ResultSet
     * @return ResultSet 데이터로 생성된 EmployeeWithLevelDto 객체
     * @throws SQLException 데이터베이스 접근 오류가 발생한 경우
     */
    public EmployeeWithLevelDto from(ResultSet rs) throws SQLException {
        return EmployeeWithLevelDto.builder()
                .eno(rs.getString("e.eno"))
                .name(rs.getString("e.name"))
                .role(rs.getLong("r.role"))
                .expiredAt(rs.getTimestamp("r.expiredAt").toLocalDateTime())
                .build();
    }
}
